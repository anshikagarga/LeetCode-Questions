import java.util.HashMap;
import java.util.List;
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String,String>map = new HashMap<>();
        for(List<String> k : knowledge){
           map.put(k.get(0), k.get(1));
        }

        StringBuilder result = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            StringBuilder key = new StringBuilder();
           
           if(s.charAt(i) == '('){
            i++;
            while(s.charAt(i) != ')'){
                key.append(s.charAt(i));
                i++;
           }
             if(map.containsKey(key.toString())){
                result.append(map.get(key.toString()));
                 

            }else{
                result.append("?");
            }
           }else{
            result.append(s.charAt(i));
           }
           
        }
        return result.toString();
    }
}
