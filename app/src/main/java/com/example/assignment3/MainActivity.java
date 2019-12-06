package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    final int[] myImageList = new int[]{R.mipmap.ic_launcher_new_cat, R.mipmap.ic_launcher_new_cat2, R.mipmap.ic_launcher_new_cat3, R.mipmap.ic_launcher_new_cat, R.mipmap.ic_launcher_new_cat2, R.mipmap.ic_launcher_new_cat4, R.mipmap.ic_launcher_new_cat4, R.mipmap.ic_launcher_new_cat3};

    int[] imageOrder = {0,1,2,3,4,5,6,7};

    int visable = 0;

    int[] tiles = new int[2];

    int[] tilesLocation = new int[2];

    public int id = 0;

    public int tileIndex(String image){
        int index = Integer.parseInt(image.replaceAll("image", ""));
        index = imageOrder[index];
        return myImageList[index];
    }

    public int tileClick(String image){
//        Log.d("myTag", image);
        if (visable == 0){
            visable++;
            tilesLocation[0] = Integer.parseInt(image.replaceAll("image", ""));
            tiles[0] = tileIndex(image);
            return 1; // unhide 1 tile
        }
        else if (visable == 1){
            visable = 0;
            tilesLocation[1] = Integer.parseInt(image.replaceAll("image", ""));
            tiles[1] = tileIndex(image);
            if (tiles[0] == tiles[1]){
                return 2; // unhide 2 tiles, then remove both
            }
            else{
                return 3; //hide 2 tiles
            }

        }
        return 99999;

    }



    public boolean isDuplicate(int[] list, int photo ){
        for(int i=0 ; i<8 ; i++){

            if (list[i] == photo){
                return true;
            }
        }
        return false;
    }


    public int rand(){
        Random random = new Random();
        Integer rand = random.nextInt(8) + 0;

        return rand;
    }

    public int[] reset(){
        int[] order = new int[8];
        int[] orderTaken = new int[8];
        int index = 0;
        for(int i=1 ; i<8 ; i++){

            do
            {
                index = rand();
            }while((isDuplicate(orderTaken, index)));
            orderTaken[i] = index;
            order[i] = index;
        }

        return order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ImageView image0= (ImageView) findViewById(R.id.image0);
         ImageView image1= (ImageView) findViewById(R.id.image1);
         ImageView image2= (ImageView) findViewById(R.id.image2);
         ImageView image3= (ImageView) findViewById(R.id.image3);
         ImageView image4= (ImageView) findViewById(R.id.image4);
         ImageView image5= (ImageView) findViewById(R.id.image5);
         ImageView image6= (ImageView) findViewById(R.id.image6);
         ImageView image7= (ImageView) findViewById(R.id.image7);



        final ImageView[] images = new ImageView[] { image0, image1, image2, image3, image4, image5, image6, image7 };


        Button button = (Button) findViewById(R.id.reset);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                imageOrder = reset();
                for(int i=0 ; i<images.length ; i++){
                    images[i].setImageResource(R.mipmap.ic_launcher_new);
                    images[i].setVisibility(View.VISIBLE);
                }

//



            }
        });

        View.OnClickListener i = new View.OnClickListener(){
            public void onClick(View v) {
                int index = tileClick(getResources().getResourceEntryName(v.getId()));

                switch(index){
                    case 1:
                        images[tilesLocation[0]].setImageResource(myImageList[imageOrder[tilesLocation[0]]]);
                        break;
                    case 2:
                        images[tilesLocation[1]].setImageResource(myImageList[imageOrder[tilesLocation[1]]]);
                        try
                        {
                            Thread.sleep(1500);
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                        v.setVisibility(View.INVISIBLE);
                        images[tilesLocation[0]].setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        for(int i=0 ; i<images.length ; i++){
                            images[i].setImageResource(R.mipmap.ic_launcher_new);
                        }

                        break;
                }


            }
        };

        image0.setOnClickListener(i);
        image1.setOnClickListener(i);
        image2.setOnClickListener(i);
        image3.setOnClickListener(i);
        image4.setOnClickListener(i);
        image5.setOnClickListener(i);
        image6.setOnClickListener(i);
        image7.setOnClickListener(i);


    }

}
