package com.auth.authentication.audio;

import com.auth.authentication.dto.response.StudentResponse;
import com.auth.authentication.service.StudentService;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import org.springframework.stereotype.Component;
import javax.sound.sampled.AudioFileFormat;
import java.io.File;


@Component
public class TextToAudioConverter {

    private static final String ROOT_FILE_PATH = System.getProperty("user.home");

    private final StudentService studentService;

    TextToAudioConverter(StudentService studentService){
        this.studentService = studentService;
        // register Kevin's voice
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    }



    public static void toAudio(String inputMessage) {
        String text = inputMessage;
        generateSpeech(text);

    }


    public static void saveAudioToDisk(StudentResponse studentResponse){
        String message = messageGenerator(studentResponse);
        String fileName = studentResponse.getName();

        String audioFilePath = ROOT_FILE_PATH + File.separator + fileName;
        SingleFileAudioPlayer audioPlayer = new SingleFileAudioPlayer(audioFilePath, AudioFileFormat.Type.WAVE);

        VoiceManager voiceManager = VoiceManager.getInstance();

        Voice voice = voiceManager.getVoice("kevin16"); // "kevin16" is one of the available voices in FreeTTS

        if (voice != null) {
            // save generated voice
            voice.setAudioPlayer(audioPlayer);
            voice.allocate();
            voice.speak(message);
            voice.deallocate();
        }

        audioPlayer.close();

    }

    private static void generateSpeech(String message){
        // Create a voice manager
        VoiceManager voiceManager = VoiceManager.getInstance();

        // Select the voice
        Voice voice = voiceManager.getVoice("kevin16"); // "kevin16" is one of the available voices in FreeTTS

        if (voice != null) {
            // Allocate the resources for the voice
            voice.allocate();

            // Convert text to speech
            voice.speak(message);

            // Deallocate the resources
            voice.deallocate();
        }
    }


    // just generate speech
    public static void toCustomAudio(StudentResponse studentResponse){
        String messageFormat = messageGenerator(studentResponse);
        generateSpeech(messageFormat);
    }

    // generate message skeleton
    public static String messageGenerator(StudentResponse studentResponse){
        String messageFormat = "Hi, My Name is  %s and you can contact me on %s. My photo is in my bio.";
        messageFormat = String.format(messageFormat, studentResponse.getName(), studentResponse.getPhoneNumber());
        return messageFormat;
    }


}

