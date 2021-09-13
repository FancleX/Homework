import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 * If the HTML file is well formatted, the method should return an empty Stack.
 * If the HTML file is not well formatted, the method should return the Stack in its current state 
   (i.e., with its current values) at the time the code determined that the tags were not balanced.
 */

public class HtmlValidator {

	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> myStack = new Stack<HtmlTag>();
		for (HtmlTag tag : tags) {
			if (tag.isOpenTag()) {
				myStack.push(tag);
			} else {
				if (!tag.isSelfClosing()) {
					// closing tag without openning tag
					if (myStack.isEmpty()) {
						return null;
					} else if (tag.matches(myStack.peek())) {
						myStack.pop();
					} else {
						return myStack;
					}
				}
			}
		}
		return myStack;
	}
}
