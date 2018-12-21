package com.example.android.cw50;

import android.content.Context;

/**
 * Created by Vikash on 11/22/2017.
 */

public class MemoryLeakDemo
{
private static MemoryLeakDemo ref;
Context ct;

private MemoryLeakDemo(Context ct)
{
    this.ct=ct;
}

public static  MemoryLeakDemo getRefrence(Context ct)
{
   if(ct==null)
   {
       ref = new MemoryLeakDemo(ct);
   }
   return ref;

}



}
