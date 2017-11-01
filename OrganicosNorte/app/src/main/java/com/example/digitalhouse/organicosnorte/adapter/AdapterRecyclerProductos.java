package com.example.digitalhouse.organicosnorte.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;
import com.example.digitalhouse.organicosnorte.view.FragmentViewPagerProductos;

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

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductosViewHolder productosViewHolder = new ProductosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product,parent,false));
        return productosViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductosViewHolder holder, final int position) {
        holder.bindProducto(listaDeProductos.get(position));
        holder.productInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapAction.goToDetail(listaDeProductos.get(position).getId());
            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listaDeProductos.get(position).getQuantity()>0) {
                    listaDeProductos.get(position).setQuantity(listaDeProductos.get(position).getQuantity() - 1);
                    holder.textViewQuantity.setText(listaDeProductos.get(position).getQuantity().toString());
                    sumInterface.quantityTapped(calculateTotal());
                }
            }
        });

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaDeProductos.get(position).setQuantity(listaDeProductos.get(position).getQuantity()+1);
                holder.textViewQuantity.setText(listaDeProductos.get(position).getQuantity().toString());
                sumInterface.quantityTapped(calculateTotal());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNombre;
        private TextView textViewDescripcion;
        private Button buttonMinus;
        private Button buttonPlus;
        private TextView textViewQuantity;
        private LinearLayout productInfo;
        private TextView textViewPrice;

        private ProductosViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewCellProductName);
            textViewDescripcion = itemView.findViewById(R.id.textViewCellProductDescription);
            textViewQuantity = itemView.findViewById(R.id.textViewCellProductQuantity);
            productInfo = itemView.findViewById(R.id.containerProductCellInfo);
            buttonMinus = itemView.findViewById(R.id.buttonCellMinus);
            buttonPlus = itemView.findViewById(R.id.buttonCellPlus);
            textViewPrice = itemView.findViewById(R.id.textViewCellProductPrice);
        }

        private void bindProducto(Producto producto){
            textViewNombre.setText(producto.getNombre());
            textViewDescripcion.setText(producto.getDescripcion());
            textViewQuantity.setText(producto.getQuantity().toString());
            if (categoryName.equals(FragmentViewPagerProductos.KEY_COMPRA)){
                textViewPrice.setText("$" + producto.getPrecioCompra().toString());
            }else {
                textViewPrice.setText("$" + producto.getPrecioVenta().toString());
            }
        }
    }

    public interface TapAction{
        public void goToDetail(String idProducto);
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
        return " " + categoryName + ": $" + total.toString();
    }
}
