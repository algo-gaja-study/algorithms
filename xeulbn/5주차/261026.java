class Solution {

    private static String[] canSpeakWords = {"aya","ye","woo","ma"};

    public int solution(String[] babbling) {
        int answer = 0;

        for(String word : babbling){
            if(canSpeak(word)){
                answer+=1;
            }
        }


        return answer;
    }


    private boolean canSpeak(String word) {
        int index = 0;         // 지금까지 읽은 글자 수
        String previous = ""; // 직전에 읽은 발음

        while (index < word.length()) {
            boolean matched = false;

            for (String sound : canSpeakWords) {
                if (word.startsWith(sound, index)) {

                    // 같은 발음이 연속하면 실패
                    if (sound.equals(previous)) {
                        return false;
                    }

                    index += sound.length();
                    previous = sound;
                    matched = true;
                    break;
                }
            }

            // 현재 위치에서 읽을 수 있는 발음이 없으면 실패
            if (!matched) {
                return false;
            }
        }

        return true;
    }
}
