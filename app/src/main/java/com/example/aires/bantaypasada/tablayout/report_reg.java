package com.example.aires.bantaypasada.tablayout;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aires.bantaypasada.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class report_reg extends Fragment {


    public report_reg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_reg, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RadioButton radio_traffic = (RadioButton) getView().findViewById(R.id.traffic);
        RadioButton radio_drivers = (RadioButton) getView().findViewById(R.id.driver);
        RadioButton radio_others = (RadioButton) getView().findViewById(R.id.others);

        final EditText body = (EditText) getView().findViewById(R.id.sms_body);
        final EditText header = (EditText) getView().findViewById(R.id.header);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        radio_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                header.setVisibility(View.GONE);
                ArrayAdapter<String> arrayAdapter;
                List<String> TrafficList;
                TrafficList = new ArrayList<>();
                TrafficList.add("Driving under the influence of liquor");
                TrafficList.add("Driving under the influence of drugs");
                TrafficList.add("Allowing an unlicensed/improperly licensed person to drive motor vehicle");
                TrafficList.add("Allowing an unlicensed/improperly licensed person to drive motor vehicle");
                TrafficList.add("Possession and use of fake/spurious Driver’s License");
                TrafficList.add("Conviction of the driver of a crime using a motor vehicle");
                TrafficList.add("Operating / driving a motor vehicle which is unregistered/improperly Registered");
                TrafficList.add("Operating a motor vehicle with unregistered substitute or replacement engine, engine block or chassis");
                TrafficList.add("Operating /allowing the operation of MV with a suspended / revoked Certificate/Official Receipt of registration");
                TrafficList.add("Tourist operating or allowing the use of non Philippine registered Motor Vehicle beyond the 90 day period of his sojourn in the country");
                TrafficList.add("Display/Use of an expired commemorative plates or stickers");
                TrafficList.add("Tampered/ marked plates or stickers ");
                TrafficList.add("Illegal transfer or use of MV regularly issued MV plates, tags or stickers except security plates on authorized Motor Vehicle");
                arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_textview, TrafficList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        });

        radio_drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                header.setVisibility(View.GONE);
                ArrayAdapter<String> arrayAdapter;
                List<String> DriversList;
                DriversList = new ArrayList<>();
                DriversList.add("1");
                DriversList.add("2");
                DriversList.add("3");
                arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_textview, DriversList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        });

        radio_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.GONE);
                header.setVisibility(View.VISIBLE);
            }
        });
        final ImageView imageView = (ImageView) getView().findViewById(R.id.imageLabel);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final CharSequence[] items = {"Remove Image"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Image Option");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Remove Image")) {
                            imageView.setImageBitmap(null);
                            imageView.setTag("");
                            imageView.setVisibility(View.GONE);
                        }
                    }
                });
                builder.show();

                return true;
            }
        });

        ImageView setImage = (ImageView) getView().findViewById(R.id.newImage);
        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        Button button = (Button) getView().findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = "";
                String theImage = "";
                if (header.isShown()) {
                    head = header.getText().toString();
                } else {
                    try {
                        head = spinner.getSelectedItem().toString();
                    } catch (Exception e) {
                    }
                }
                final String smsBody = body.getText().toString();
                theImage = imageView.getTag().toString();

                if (head.equals("")) {
                    Toast.makeText(getContext(), "Empty header", Toast.LENGTH_SHORT).show();
                } else if (smsBody.equals("")) {
                    Toast.makeText(getContext(), "Empty details", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), head + "\n" + "\n" + smsBody +"\n"+"\n"+theImage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void selectImage() {
        final int REQUEST_CAMERA = 0, SELECT_FILE = 1;
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final ImageView imageView = (ImageView) getView().findViewById(R.id.imageLabel);
        int REQUEST_CAMERA = 0, SELECT_FILE = 1;

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String path = destination.getAbsolutePath();
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(thumbnail);
                imageView.setTag(path);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(getActivity(), selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bm);
//                Toast.makeText(getActivity(),bm.toString(),Toast.LENGTH_LONG).show();
                imageView.setTag(selectedImagePath);

            }
        }
    }
}
