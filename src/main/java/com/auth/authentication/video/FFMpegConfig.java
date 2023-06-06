package com.auth.authentication.video;


import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FFMpegConfig {

    @Bean
    public FFmpeg getFFmpeg() throws IOException {
        return new FFmpeg();
    }

    public FFprobe getFFprobe() throws IOException {
        return new FFprobe();
    }
}
