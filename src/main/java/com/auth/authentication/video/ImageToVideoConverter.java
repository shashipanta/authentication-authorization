package com.auth.authentication.video;


import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@RequiredArgsConstructor
@Component
public class ImageToVideoConverter {

    private final FFmpeg fFmpeg;
//    private final FFprobe fFprobe;

    private static String ROOT_DIR = System.getProperty("user.home")
            + File.separator
            + "Desktop/File_upload";

    private byte[] getStoredImageInByteArr(String filePath) {

        File storedFile = new File(ROOT_DIR + File.separator + filePath);

        byte[] imageBytes = new byte[0];
        try {
            imageBytes = FileUtils.readFileToByteArray(storedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }


    public void generateVideo(String fileName) {
        // Set video generation parameters
        String sourceImage = ROOT_DIR + File.separator + fileName;
        String generatedVideoName = fileName + ".mp4";
        int framesPerSecond = 30;

        String ffmpegCommand = "-f" + sourceImage + "-i *.png" + sourceImage + generatedVideoName;


//        ffmpeg -f image2 -i img%d.jpg /tmp/a.mpg
//        fFmpeg.builder()
//                .setInput(sourceImage)
//                .addExtraArgs(ffmpegCommand)
//

        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(sourceImage)     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists

//                .addOutput("output.mp4")   // Filename for the destination
                .setFormat("mp4");      // Format is inferred from filename, or can be set

//        FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);

        // Run a one-pass encode
//        executor.createJob(builder).run();


    }
}





