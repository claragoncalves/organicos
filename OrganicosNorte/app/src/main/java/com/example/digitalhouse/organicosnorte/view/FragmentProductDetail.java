package com.example.digitalhouse.organicosnorte.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.Producto;


public class FragmentProductDetail extends Fragment {

    public static final String KEY_IDPRODUCTO = "idProducto";
    private Producto producto;
    private ProductModification productModification;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPrecioCompra;
    private EditText editTextPrecioVenta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Bundle bundle = getArguments();

        producto = ProductoController.getProduct(getContext(), bundle.getInt(KEY_IDPRODUCTO));

        editTextTitle = view.findViewById(R.id.textViewDetailTitle);
        editTextTitle.setText(producto.getNombre());
        editTextDescription = view.findViewById(R.id.textViewDetailDescripcion);
        editTextDescription.setText(producto.getDescripcion());
        editTextPrecioCompra = view.findViewById(R.id.textViewDetailPrecioCompra);
        editTextPrecioCompra.setText(producto.getPrecioCompra().toString());
        editTextPrecioVenta = view.findViewById(R.id.textViewDetailPrecioVenta);
        editTextPrecioVenta.setText(producto.getPrecioVenta().toString());

        Button buttonEraseProduct = view.findViewById(R.id.buttonEraseProduct);
        buttonEraseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productModification = (ProductModification) getContext();
                productModification.deleteProduct(producto);
            }
        });

        Button buttonModifyProduct = view.findViewById(R.id.buttonModifyProduct);
        buttonModifyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productModification = (ProductModification) getContext();
                productModification.modifyProduct(bindProductModification(producto.getId()));
            }
        });
        return view;
    }

    public interface ProductModification{
        public void deleteProduct(Producto producto);
        public void modifyProduct(Producto producto);
    }

    public Producto bindProductModification(Integer idProducto){
        Producto productBinded = new Producto(idProducto, editTextTitle.getText().toString(), editTextDescription.getText().toString(), Double.parseDouble(editTextPrecioCompra.getText().toString()), Double.parseDouble(editTextPrecioVenta.getText().toString()));
        return productBinded;
    }
}
