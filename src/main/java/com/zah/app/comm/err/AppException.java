package com.zah.app.comm.err;

/**
 * Created by zah on 2017/5/22.
 */
public class AppException extends  Exception {
     Err err;
     String msg;
     public AppException(Err err,String msg){
         this.err=err;
         this.msg=msg;
     }
     public AppException(Err err){
         this.err=err;
         this.msg=err.getErrorMsg();
     }
    public Err getErr() {
        return err;
    }

    @Override
    public String getMessage() {
         return msg;

    }
}
