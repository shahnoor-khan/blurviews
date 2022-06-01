package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.solver.state.State
import androidx.constraintlayout.widget.ConstraintLayout
import com.contentarcade.adnan.shapedblurlibrary.GaussianBlur
import com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [img.newInstance] factory method to
 * create an instance of this fragment.
 */
class img : Fragment() ,View.OnTouchListener{

    private var dX by Delegates.notNull<Float>()
    private var dY by Delegates.notNull<Float>()
    private lateinit var constraint:ConstraintLayout
    val fx = -2638.0
    val fy = -2310.0
    var ignore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_img, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraint = view.findViewById(R.id.constraint)
        val img  = view.findViewById<ImageView>(R.id.imageView5)
        val x = view.findViewById<ShapeLayout>(R.id.circle)



        fun applyBlurView(r : Int, size : Int) {
            val siz1 = size.toFloat()
            GaussianBlur.with(context)
                .size(siz1)
                .radius(r)
                .put(R.drawable.download, img)

        }


        applyBlurView(25, 100)
        x.setTypeOfShape(ShapeLayout.ShapeType.CIRCLE)
        x.resetShapeSize(70)
        x.setOnTouchListener(this)


    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        var newxx:Float
        var newyy:Float
        val newX: Float
        val newY: Float
        val x:Float
        val y :Float
        if (ignore&& event!!.action !=MotionEvent.ACTION_UP ){
            return false
        }


        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                dX =event.x
                dY =event.y
                newxx = v!!.x
                newyy = v.y

                val f = event.rawX
                Log.d("opps","${v?.x}")
                Log.d("yyg","${v!!.y}")
                Log.d("dxx","${dX}")
                Log.d("dxy","${event.rawX}")

            }


            MotionEvent.ACTION_MOVE -> {

                newX = event.x - dX
                newY = event.y - dY

                if ( (v!!.x >= fx - constraint.width/2 - 100 ) && (v.x <=fx +  constraint.width/2 - 100) && (v.y >= fy  - constraint.height/2 - 100 )&& (v.y <= fy + constraint.height/2 - 100)) {
                    v!!.animate()
                        .x(v.x + newX)
                        .y(v.y + newY)
                        .setDuration(0)
                        .start()
                    newxx = v.x
                    newyy = v.y
                    Log.d("new","$newxx")
                    Log.d("lim","${v!!.x - 100 }")
                    Log.d("cons","${constraint.width}")
                    Log.d("yyy","$newY")
                }
                else{
                    v!!.animate()
                        .x((fx).toFloat())
                        .y((fy).toFloat())
                        .setDuration(0)
                        .start()
                    ignore = true
                }

            }
            MotionEvent.ACTION_UP ->{
                ignore = false
            }

            else -> return false
        }
        return true

    }

}
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        if (event != null) {
//            when (event.getAction()) {
//
//                MotionEvent.ACTION_DOWN ->{
//                    dX = v!!.getX() - event.rawX
//                    dY = v.getY() - event.rawY
//                }
//
//                MotionEvent.ACTION_MOVE ->{
//                    val x = event.rawX + dX
//                    val y = event.rawY + dY
//                    if((x <= 0 || x >= constraint.width - v!!.width) || (y <= 0 || y >= constraint.height - v.height)){
//                        return false
//                    }
//                    v!!.animate()
//                        .x(event.rawX + dX)
//                        .y(event.rawY + dY)
//                        .setDuration(0)
//                        .start()
//                }
//                else ->
//                    return false
//            }
//        }
//        return true
//    }

