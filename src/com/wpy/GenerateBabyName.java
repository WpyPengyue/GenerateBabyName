package com.wpy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenerateBabyName {

    public static void main(String[] args) {
        List<String> names = generateBabyName();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            System.out.print(name + " ");
            if((i + 1) % 20 == 0) {
                System.out.println();
            }
        }
    }

    private static List<String> generateBabyName() {
        List<String> names = new ArrayList<>();
        Properties properties = new Properties();
        InputStream inputStream = Object.class.getResourceAsStream("/happyWord.properties");
        try {
            properties.load(inputStream);
            String familyName = PropertiesUtil.getProperties(properties,"familyName");
            List<String> firstHappyWords = convertStrToWordList(PropertiesUtil.getProperties(properties,"firstHappyWords"));
            List<String> secondHappyWords = convertStrToWordList(PropertiesUtil.getProperties(properties,"secondHappyWords"));
            List<String> happyWords = convertStrToWordList(PropertiesUtil.getProperties(properties,"happyWords"));
            names.addAll(generateBabyName(familyName, happyWords, firstHappyWords, secondHappyWords));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private static List<String> generateBabyName(String familyName, List<String> happyWords) {
        return generateBabyName(familyName, happyWords, null, null);
    }

    private static List<String> generateBabyName(String familyName, List<String> happyWords, List<String> firstHappyWords, List<String> secondHappyWords) {
        if (firstHappyWords == null) {
            firstHappyWords = new ArrayList<>();
        }
        if (secondHappyWords == null) {
            secondHappyWords = new ArrayList<>();
        }
        firstHappyWords.addAll(happyWords);
        secondHappyWords.addAll(happyWords);
        List<String> names = new ArrayList<>();
        for (String firstWord: firstHappyWords) {
            for (String secondWord: secondHappyWords) {
                if(firstWord.equals(secondWord)){
                    continue;
                }
                String name = familyName + firstWord + secondWord;
                names.add(name);
            }
        }
        return names;
    }

    private static List<String> convertStrToWordList(String str){
        String[] wordArr = str.split(",");
        List<String> words = new ArrayList<>();
        for (int i = 0; i < wordArr.length; i++) {
            String word = wordArr[i].trim();
            if ("".equals(word)){
                continue;
            }
            if(!words.contains(word)){
                words.add(wordArr[i].trim());
            }
        }
        return words;
    }

}
