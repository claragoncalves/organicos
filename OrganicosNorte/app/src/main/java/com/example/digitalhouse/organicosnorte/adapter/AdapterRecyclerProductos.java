package com.example.digitalhouse.organicosnorte.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;
import com.example.digitalhouse.organicosnorte.view.FragmentViewPagerProductos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/26/17.
 */

public class AdapterRecyclerProductos extends RecyclerView.Adapter<AdapterRecyclerProductos.ProductosViewHolder>{
    private List<Producto> listaDeProductos;
    private TapAction tapAction;
    private String categoryName;
    private SumInterface sumInterface;

    public AdapterRecyclerProductos(List<Producto> listaDeProductos, Context context, String categoryName, SumInterface sumInterface) {
        this.listaDeProductos = listaDeProductos;
        tapAction = (TapAction) context;
        this.categoryName = categoryName;
        this.sumInterface = sumInterface;
    }

    public List<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductosViewHolder productosViewHolder = new ProductosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product,parent,false));
        return productosViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductosViewHolder holder, final int position) {
        holder.bindProducto(listaDeProductos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNombre;
        private TextView textViewDescripcion;
        private TextView textViewPrice;
        private DecimalFormat df;
        private EditText editTextQuantity;

        private ProductosViewHolder(final View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewCellProductName);
            textViewDescripcion = itemView.findViewById(R.id.textViewCellProductDescription);
            textViewPrice = itemView.findViewById(R.id.textViewCellProductPrice);
            editTextQuantity = itemView.findViewById(R.id.editTextCellProductQuantity);
            df = new DecimalFormat("####0.00");

            editTextQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        listaDeProductos.get(getAdapterPosition()).setQuantity(Integer.parseInt(editTextQuantity.getText().toString()));
                        sumInterface.quantityTapped(calculateTotal());
                        notifyDataSetChanged();
                        InputMethodManager inputMethodManager = (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(editTextQuantity.getWindowToken(), 0);
                        return true;
                    }
                    return false;
                }
            });

            //todo cuando pierde foco calcular pero crashea
//            editTextQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    sumInterface.quantityTapped(calculateTotal());
//                }
//            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tapAction.goToDetail(listaDeProductos.get(getAdapterPosition()).getId());
                }
            });
        }

        private void bindProducto(Producto producto){
            textViewNombre.setText(producto.getNombre());
            textViewDescripcion.setText(producto.getDescripcion());
            if (producto.getQuantity() >0) {
                editTextQuantity.setText(producto.getQuantity().toString());
            } else {
                editTextQuantity.setText("");
            }
            if (categoryName.equals(FragmentViewPagerProductos.KEY_COMPRA)){
                textViewPrice.setText("$" + df.format(producto.getPrecioCompra()));
            }else {
                textViewPrice.setText("$" + df.format(producto.getPrecioVenta()));
            }
        }
    }

    public interface TapAction{
        public void goToDetail(Integer idProducto);
    }

    public interface SumInterface{
        public void quantityTapped(String total);
    }

    private String calculateTotal(){
        Double total = 0.0;
        if (categoryName.equals(FragmentViewPagerProductos.KEY_COMPRA)) {
            for (Producto product : listaDeProductos) {
                total = total + product.getPrecioCompra() * product.getQuantity();
            }
        }else {
           for (Producto product : listaDeProductos) {
                total = total + product.getPrecioVenta() * product.getQuantity();
           }
        }

        DecimalFormat df = new DecimalFormat("####0.00");

        return " " + categoryName + ": $" + df.format(total);
    }
}
