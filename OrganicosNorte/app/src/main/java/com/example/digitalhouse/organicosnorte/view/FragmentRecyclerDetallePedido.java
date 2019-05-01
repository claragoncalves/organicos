package com.example.digitalhouse.organicosnorte.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitalhouse.organicosnorte.R;
import com.example.digitalhouse.organicosnorte.adapter.AdapterRecyclerDetallesPedido;
import com.example.digitalhouse.organicosnorte.controller.PedidoController;
import com.example.digitalhouse.organicosnorte.controller.PedidoDetalleController;
import com.example.digitalhouse.organicosnorte.controller.ProductoController;
import com.example.digitalhouse.organicosnorte.model.pojo.PedidoDetalle;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerDetallePedido extends Fragment {
    public static final String ID_PEDIDO = "idPedido";
    private DeletePedido deletePedido;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private ImageButton imageButtonBorrarPedido;
    private ImageButton imageButtonSharePedido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_detalle_pedido, container, false);
        final Bundle bundle = getArguments();

        List<PedidoDetalle> detallesPedido = PedidoDetalleController.getProductosDePedido(getContext(),bundle.getInt(ID_PEDIDO));
        constraintLayout = view.findViewById(R.id.recyclerDetallesPedidosLayout);

        recyclerView = view.findViewById(R.id.recyclerDetallesPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        AdapterRecyclerDetallesPedido adapterRecyclerDetallesPedido = new AdapterRecyclerDetallesPedido(detallesPedido);
        recyclerView.setAdapter(adapterRecyclerDetallesPedido);

        imageButtonBorrarPedido = view.findViewById(R.id.buttonEliminarDetallesPedidos);
        imageButtonBorrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePedido = (DeletePedido) getContext();
                deletePedido.deletePedido(bundle.getInt(ID_PEDIDO));
            }
        });

        imageButtonSharePedido = view.findViewById(R.id.buttonShareDetallesPedidos);
        imageButtonSharePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWriteStoragePermissionGranted()) {
                   shareDetails();
                }
            }
        });


        TextView textView = view.findViewById(R.id.recyclerDetallesPedidosTextViewTotalPrice);
        Double totalPrice = 0.0;
        for (PedidoDetalle pedidoDetalle:detallesPedido) {
            if (PedidoController.getPedido(getContext(), bundle.getInt(ID_PEDIDO)).getCompraVenta()) {
                totalPrice = totalPrice + ProductoController.getProduct(getContext(), pedidoDetalle.getIdProducto()).getPrecioVenta() * pedidoDetalle.getCantidad();
            }else{
                totalPrice = totalPrice + ProductoController.getProduct(getContext(), pedidoDetalle.getIdProducto()).getPrecioCompra() * pedidoDetalle.getCantidad();
            }
        }

        textView.setText("Precio: " + totalPrice.toString());
        return view;
    }

    public interface DeletePedido{
        public void deletePedido(Integer idPedido);
    }


    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 3:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //resume tasks needing this permission
                    shareDetails();
                }else{

                }
                break;
        }
    }

    public void shareDetails(){
        imageButtonBorrarPedido.setVisibility(View.INVISIBLE);
        imageButtonSharePedido.setVisibility(View.INVISIBLE);
        constraintLayout.setDrawingCacheEnabled(true);
        Bitmap bitmap = getBitmapFromView(constraintLayout); // here give id of our root layout (here its my FrameLayout's id)
        constraintLayout.setDrawingCacheEnabled(false);
        imageButtonBorrarPedido.setVisibility(View.VISIBLE);
        imageButtonSharePedido.setVisibility(View.VISIBLE);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_STREAM, getImageUri(getContext(), bitmap));
        try {
            startActivity(Intent.createChooser(i, "Compartir ..."));
        } catch (android.content.ActivityNotFoundException ex) {

            ex.printStackTrace();
        }
    }
}
