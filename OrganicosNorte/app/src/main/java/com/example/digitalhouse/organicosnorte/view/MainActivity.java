package com.example.digitalhouse.organicosnorte.view;

import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerProductos;
import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerProductos.TapAction, FragmentAddProduct.AddProduct, FragmentProductDetail.ProductModification {

    TextView textViewTotal;
    private static final String KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA = "fragmentViewPagerCompraVenta";
    private static final String KEY_FRAGMENT_PRODUCTLIST = "fragmentProductList";
    private static final String KEY_FRAGMENT_ADDPRODUCT = "fragmentAddProduct";
    private static final String KEY_FRAGMENT_PRODUCTDETAIL = "fragmentProductDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar miToolbar = findViewById(R.id.toolbarAct1);
        miToolbar.setTitle("Organicos Norte");
        miToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(miToolbar);

        loadFragment(new FragmentViewPagerProductos(), KEY_FRAGMENT_VIEWPAGERCOMPRAVENTA);
        textViewTotal = findViewById(R.id.textViewTotalPrice);
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
    public void goToDetail(String idProducto) {
        FragmentProductDetail fragmentProductDetail = new FragmentProductDetail();
        Bundle bundle = new Bundle();
        bundle.putString(FragmentProductDetail.KEY_IDPRODUCTO, idProducto);
        fragmentProductDetail.setArguments(bundle);
        loadFragment(fragmentProductDetail, KEY_FRAGMENT_PRODUCTDETAIL);
    }


    @Override
    public void deleteProduct(Producto producto) {
        ProductoController.deleteProduct(this, producto);
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
}