package com.zy.javaspi;

public class YoukuCDN implements UploadCDN {
    @Override
    public void upload(String yrl) {
        System.out.println("上传到优酷");
    }
}
