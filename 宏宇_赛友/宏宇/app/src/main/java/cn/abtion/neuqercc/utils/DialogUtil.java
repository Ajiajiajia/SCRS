package cn.abtion.neuqercc.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * @author lszr
 * @since 2017/12/1 下午7:58
 * email wsyglszr@gmail.com
 */


public final class DialogUtil {


    /**
     * 原生Dialog类，包括builder的初始化方法：singleInit；showNativeDialog方法；
     * 设置两个按钮的方法：setPositiveButton和setNegativeButton
     * 设置单选框方法：setSingleChoice，复选框：setMultiChoice
     * 设置title和Message方法
     *
     * 使用时实例化一个对象，同时先调用singleInit方法，使用对象调用方法
     *
     */
    public class NativeDialog{
        public AlertDialog.Builder getBuilder() {
            return builder;
        }

        private  AlertDialog.Builder builder;

        public NativeDialog() {

        }

        /**
         * show

         * @return
         */
        public AlertDialog.Builder showNativeDialog(){
            builder.show();
            return builder;
        }


        /**
         * 初始化builder
         * @param context
         * @return
         */
        public NativeDialog singleInit(Context context){
            builder=new AlertDialog.Builder(context);
            return this;
        }

        /**
         * 设置取消按钮
         * @param negativeButton
         * @param onClickButton
         * @return
         */
        public NativeDialog setNegativeButton(String negativeButton,DialogInterface.OnClickListener onClickButton){
            builder.setNegativeButton(negativeButton,onClickButton);

            return this;
        }

        public NativeDialog setNegativeButton(String negativeButton){
            builder.setNegativeButton(negativeButton,null);

            return this;
        }

        /**
         * 设置确定按钮
         * @param positiveButton
         * @param onClickButton
         * @return
         */
        public NativeDialog setPositiveButton(String positiveButton,DialogInterface.OnClickListener onClickButton){
            builder.setPositiveButton(positiveButton,onClickButton);

            return this;
        }

        public NativeDialog setPositiveButton(String positiveButton){
            builder.setPositiveButton(positiveButton,null);

            return this;
        }

        /**
         * 设置单选框（原生）
         * @param singleItemString
         * @param onClickListener
         * @return
         */
        public NativeDialog setSingleChoice(final String singleItemString[], DialogInterface.OnClickListener onClickListener){
            if(singleItemString!=null){
                builder.setSingleChoiceItems(singleItemString, -1, onClickListener);
            }
            return this;
        }

        public NativeDialog setSingleChoice(final String singleItemString[],int checkedId, DialogInterface.OnClickListener onClickListener){
            if(singleItemString!=null){
                builder.setSingleChoiceItems(singleItemString, checkedId, onClickListener);
            }
            return this;
        }

        public NativeDialog setTitle(String title){
            builder.setTitle(title);
            return this;
        }
        public NativeDialog setMessage(String message){
            builder.setMessage(message);
            return this;
        }

        /**
         * 设置复选框
         * @param multiItemString
         * @param onClickListener
         * @return
         */
        public NativeDialog setMultiChoice(final String multiItemString[],DialogInterface.OnMultiChoiceClickListener onClickListener){
            if(multiItemString!=null) {
                builder.setMultiChoiceItems(multiItemString, null, onClickListener);
            }
            return this;
        }


    }


    /**
     * 自定义Dialog类：
     * 使用时要先调用initDialog方法：参数：context，自定义布局id，style的id
     * 类中有view成员，可以使用getView得到，从而使用findViewById绑定对应布局的控件
     *
     */
    public class CustomAlertDialog {

        private  AlertDialog.Builder builder;
        private  View view;

        public CustomAlertDialog showDialog(){
            builder.show();
            return this;
        }

        /**
         * 获取view
         * @return
         */
        public View getView(){
            return view;
        }
        /**
         * 初始化Dialog，设置view
         *
         * @param context
         * @param itemView
         * @return
         */
        public CustomAlertDialog initDialog(Context context, @LayoutRes int itemView, int styleId){
            builder=new AlertDialog.Builder(context,styleId);
            view=View.inflate(context,itemView,null);
            builder.setView(view);

            return this;
        }

        public CustomAlertDialog initDialog(Context context, @LayoutRes int itemView){
            builder=new AlertDialog.Builder(context);
            view=View.inflate(context,itemView,null);
            builder.setView(view);

            return this;
        }

        /**
         * 设置gravity
         * @param gravity
         * @return
         */
        public CustomAlertDialog setGravity(int gravity){
            Window window=builder.create().getWindow();
            window.setGravity(gravity);
            return this;
        }

        /**
         * 设置点击外部是否取消
         * @param bool
         * @return
         */
        public CustomAlertDialog setCanceledOntouchOutside(boolean bool){
            builder.create().setCanceledOnTouchOutside(bool);
            return this;
        }

        /**
         * 设置Dialog的宽高
         * @param height
         * @param width
         * @return
         */
        public CustomAlertDialog setHeightAndWidth(int height, int width){
            Window window=builder.create().getWindow();
            WindowManager.LayoutParams lp=window.getAttributes();
            lp.width=width;
            lp.height=height;
            window.setAttributes(lp);

            return this;
        }

        /**
         *
         * 设置padding
         *
         * @param left
         * @param right
         * @param top
         * @param bottom
         * @return
         */
        public CustomAlertDialog setPadding(int left, int right, int top, int bottom){
            Window window=builder.create().getWindow();
            window.getDecorView().setPadding(left,top,right,bottom);

            return this;
        }

        /**
         * 设置位置
         * @param x
         * @param y
         * @return
         */
        public CustomAlertDialog setPosition(int x, int y){
            Window window=builder.create().getWindow();
            WindowManager.LayoutParams lp=window.getAttributes();
            lp.x=x;
            lp.y=y;

            window.setAttributes(lp);


            return this;
        }

        public  AlertDialog.Builder getBuilder() {
            return builder;
        }

        /**
         * 设置背景透明度

         * @param transparency
         * @return
         */
        public CustomAlertDialog setTransparency(int transparency){
            Window window=builder.create().getWindow();
            WindowManager.LayoutParams lp=window.getAttributes();
            lp.alpha=transparency;
            window.setAttributes(lp);

            return this;
        }




    }


    /**
     *
     */
    public class NativeProgressDialog {

        private  ProgressDialog nativeProgressDialog;

        public NativeProgressDialog initDialog(Context context){
            nativeProgressDialog =new ProgressDialog(context);
            return this;
        }

        public NativeProgressDialog setMessage(String message){
            nativeProgressDialog.setMessage(message);

            return this;
        }

        /**
         * 显示Dialog
         * @param context
         * @return
         */
        public NativeProgressDialog showDialog(Context context){
            nativeProgressDialog.show();

            return this;
        }

        /**
         * 设置标题
         * @param title
         * @return
         */
        public NativeProgressDialog setDialogTitle(String title){
            nativeProgressDialog.setTitle(title);

            return  this;
        }

        /**
         * 设置外部点击是否可以取消
         * @param bool
         * @return
         */
        public NativeProgressDialog setCanceledOutside(boolean bool){
            nativeProgressDialog.setCanceledOnTouchOutside(bool);

            return this;
        }

        /**
         * 设置进度条的形式
         * @param style
         * @return
         */
        public NativeProgressDialog setProgressDialogStyle(int style){
            nativeProgressDialog.setProgressStyle(style);

            return this;
        }

        /**
         * 设置title图标
         * @param titleImage
         * @return
         */
        public NativeProgressDialog setIcon(int titleImage){
            nativeProgressDialog.setIcon(titleImage);

            return this;
        }

        /**
         * 设置内容
         * @param message
         * @return
         */
        public NativeProgressDialog setDialogMessage(String message){
            nativeProgressDialog.setMessage(message);

            return this;
        }

    }




}

