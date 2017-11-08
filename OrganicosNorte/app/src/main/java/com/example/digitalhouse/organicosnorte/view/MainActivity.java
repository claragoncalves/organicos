package com.example.digitalhouse.organicosnorte.view;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerPedidos;
import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerProductos;
import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.PedidoController;
import com.example.digitalhouse.organicosnorte.controller.PedidoDetalleController;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Pedido;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerProductos.TapAction, FragmentAddProduct.AddProduct, FragmentProductDetail.ProductModification, FragmentRecyclerProductos.PedidoAddListener, AdapterRecyclerPedidos.PedidoTapped {

    private static final String KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA = "fragmentViewPagerCompraVenta";
    private static final String KEY_FRAGMENT_ADDPRODUCT = "fragmentAddProduct";
    private static final String KEY_FRAGMENT_PRODUCTDETAIL = "fragmentProductDetail";
    private static final String KEY_FRAGMENT_DETALLESPEDIDOS = "fragmentDetallesPedidos";
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar miToolbar = findViewById(R.id.toolbarAct1);
        miToolbar.setTitle("Organicos Norte");
        //miToolbar.setNavigationIcon(R.drawable.);
        miToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(miToolbar);
        container = findViewById(R.id.fragmentContainerMain);
        loadFragment(new FragmentViewPagerProductos(), KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA);
    }

    private void loadFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentActual = fragmentManager.findFragmentById(R.id.fragmentContainerMain);
        if (fragmentActual == null || !fragmentActual.getTag().equals(tag)) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerMain, fragment, tag);
            if (fragmentActual != null && fragmentActual.getTag().equals(KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA)) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void goToDetail(Integer idProducto) {
        FragmentProductDetail fragmentProductDetail = new FragmentProductDetail();
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentProductDetail.KEY_IDPRODUCTO, idProducto);
        fragmentProductDetail.setArguments(bundle);
        loadFragment(fragmentProductDetail, KEY_FRAGMENT_PRODUCTDETAIL);
    }


    @Override
    public void deleteProduct(Producto producto) {
        ProductoController.deleteProduct(this, producto);
        loadFragment(new FragmentViewPagerProductos(), KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA);
    }

    @Override
    public void modifyProduct(Producto producto) {
        ProductoController.modifyProduct(this,producto);
        loadFragment(new FragmentViewPagerProductos(), KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA);
    }

    @Override
    public void addProduct(Producto producto) {
        ProductoController.insertProduct(this, producto);
        loadFragment(new FragmentViewPagerProductos(), KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addAction:
                loadFragment(new FragmentAddProduct(), KEY_FRAGMENT_ADDPRODUCT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void agregarPedido(Pedido pedido, List<PedidoDetalle> pedidoDetalles) {
        long idPedido = PedidoController.insertPedido(this, pedido);
        for (PedidoDetalle pedidoDetalle:pedidoDetalles) {
            pedidoDetalle.setIdPedido((int)(long)idPedido);
        }
        agregarDetallePedido(pedidoDetalles);
    }

    public void agregarDetallePedido(List<PedidoDetalle> pedidoDetalles) {
        PedidoDetalleController.insertDetallesPedido(this, pedidoDetalles);
        Snackbar.make(container,"PEDIDO AGREGADO",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void pedidoSeleccionado(Integer idPedido) {
        FragmentRecyclerDetallePedido fragmentRecyclerDetallePedido = new FragmentRecyclerDetallePedido();
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentRecyclerDetallePedido.ID_PEDIDO, idPedido);
        fragmentRecyclerDetallePedido.setArguments(bundle);
        loadFragment(fragmentRecyclerDetallePedido,KEY_FRAGMENT_DETALLESPEDIDOS);
    }
}