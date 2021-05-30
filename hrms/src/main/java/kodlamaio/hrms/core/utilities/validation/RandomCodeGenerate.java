package kodlamaio.hrms.core.utilities.validation;

import java.util.Random;

public class RandomCodeGenerate {

	public String create() {
        int leftLimit = 86; 
        int rightLimit = 115; 
        int targetStringLength = 41;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
	}
}
