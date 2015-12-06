package daumtrack.kdh.termproject.sample;

import com.google.common.base.Joiner;
import daumtrack.kdh.termproject.batch.ext.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * StringTokenSorter.
 *
 * @author mitchell.geek
 */
public class StringTokenSorter implements Converter<String, String> {
    @Override
    public String convert(String item) {
        // tokenize
        String[] tokens = item.split("\\s");
        // convert to int array
        List<Integer> nums = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            nums.add(Integer.valueOf(token));
        }
        // sort
        Collections.sort(nums);
        return Joiner.on(" ").join(nums);
    }
}
