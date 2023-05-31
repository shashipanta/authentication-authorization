package com.auth.authentication.audio;

import com.auth.authentication.dto.response.StudentResponse;
import com.auth.authentication.service.StudentService;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.springframework.stereotype.Component;

@Component
public class TextToAudioConverter {


    private final StudentService studentService;

    TextToAudioConverter(StudentService studentService){
        this.studentService = studentService;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    }



    public static void toAudio(String inputMessage) {



        String text = inputMessage;

        generateSpeech(text);


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


    public static void toCustomAudio(StudentResponse studentResponse){

        String messageFormat = "Hi, My Name is  %s and you can contact me on %s. My photo is in my bio.";

        messageFormat = String.format(messageFormat, studentResponse.getName(), studentResponse.getPhoneNumber());

        generateSpeech(messageFormat);
    }


}

