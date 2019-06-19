package training.m2i.ltmgestures;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Matrix _matrix = new Matrix();
    private Matrix _savedMatrix = new Matrix();
    private PointF _start = new PointF();
    static  final  int  NONE  =  0;
    static  final  int  DRAG  =  1;
    int  mode  =  NONE;

    private ImageView _imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _imageView = (ImageView)findViewById( R.id.imageView1 );
        // set the event listener to the imageView
        _imageView.setOnTouchListener( this );
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch( event.getAction() & MotionEvent.ACTION_MASK ) {
            case MotionEvent.ACTION_DOWN:
                _savedMatrix.set( _matrix );
                _start.set( event.getX(), event.getY() );
                mode  =  DRAG;
                Log.v( "ltm", "action_down" );
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode  =  NONE;
                Log.v( "ltm", "action_up" );
                break;

            case MotionEvent.ACTION_MOVE:
                if  ( mode  ==  DRAG )  {
                    _matrix.set( _savedMatrix );
                    _matrix.postTranslate( event.getX() - _start.x, event.getY() - _start.y );
                    Log.v( "ltm", "action_move" );
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                break;
        }

        _imageView.setImageMatrix( _matrix );

        return true; // si false alors les autres gestures fonctionnent
    }
}
