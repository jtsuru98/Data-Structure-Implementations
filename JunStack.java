import java.util.*;

//Guidance Documentation (if interested)
//https://medium.com/swift2go/stacks-and-lifo-structures-implementation-and-use-cases-7ada8f8c400

public class JunStack
{
    private String[] stack;
    private int trackStackPosition;
    
    public static void main(String[] args) {
        System.out.println("Input memory for initial stack");
        
        Scanner userInput = new Scanner(System.in);
        int memory = userInput.nextInt(); // user memory input
        
        //logic to determine initial stack memory capacity and values
        if (memory == 0) {
            JunStack userList = new JunStack();
            System.out.println("Your starter stack is: " + Arrays.toString(userList.stack) + "\n");
            
            System.out.println("Let's dive in!");
            userList.stackCommands();
            
        } else if (memory > 0) {
            JunStack userList = new JunStack(memory);
            System.out.println("Your starter Stack is: " + Arrays.toString(userList.stack) + "\n");
            
            System.out.println("Let's dive in!");
            userList.stackCommands();
            
        } else {
            System.out.println("Invalid memory capacity.");
            return;
        }
    }
    
    public JunStack() {
        //constructor that takes no parameter but initializes an empty list
        trackStackPosition = 0;
        stack = new String[1]; // prints null
        stack[0] = null;
    }

    public JunStack(int memory) {
        //constructor that takes parameter argument
        Scanner userInput = new Scanner(System.in);

        trackStackPosition = memory;
        stack = new String[memory * 2];
		int i = 0;

		//this shit needs to account for more than 1 or less than 1 character
        while (i < memory) {
            System.out.println("Please input 1 character per memory block in your array. We are at index " + i);
			String value = userInput.next().trim();
			
			if (value.length() == 1){
				stack[i] = value;
				i += 1;
			} else {
				System.out.println("Invalid input. Must be length 1. Try again."); 
			}
        }
    }
    
    public static void stackOperations(){
        System.out.println("Definition:");
        System.out.println("A stack is a linear data structure that follows the principle of Last In First Out (LIFO)."); 
        System.out.println("This means the last element inserted inside the stack is removed first."); 
        System.out.println("Operations:");
        System.out.println("Push: Add an element to the top of a stack.");
        System.out.println("Pop: Remove an element from the top of a stack.");
        System.out.println("IsEmpty: Check if the stack is empty.");
        System.out.println("IsFull: Check if the stack is full.");
        System.out.println("Peek: Get the value of the top element without removing it.");
        System.out.println("Change: Change char at specific index.");
        System.out.println("Count: Count the number of values in the stack.");
        System.out.println("Display: Display all values in the stack.");
        System.out.println("TotalPop: Empty out the stack.\n");
    }
    
    public void stackCommands(){
        System.out.println("Start manipulating your Stack. Each command executes one time. You will have to refresh/give command after each command executes one time.");
        System.out.println("When done, input 'done' and the Stack will pop to empty. If you need command specifications, input 'specs'.");
        
        
        Scanner userInput = new Scanner(System.in);
        boolean done = false;
        
        //this is the logic to manipulate the stack
        label:
        while (!done) {
            String command = userInput.next().toLowerCase().trim();
            
            if (trackStackPosition <= stack.length / 4){ //garbage collection if array size is 4x as big as the # of values in it
                    garbageCollection();
                }
            
            switch (command) {
                case "done":
                    done = true;
                    break label;
                    
                case "specs":
                    stackOperations();
                    break;
                    
                case "push":
                    System.out.println("Input character to add to top of the stack.");
                    String value_to_add = userInput.next();
                    
                    push(value_to_add);
                    System.out.println(Arrays.toString(this.stack));
                    break;
                    
                case "pop":
                    pop();
                    System.out.println(Arrays.toString(this.stack));
                    break;
                    
                case "isempty":
                    isEmpty();
                    System.out.println(isEmpty());
                    break;
                    
                case "isfull":
                    isFull();
                    System.out.println(isFull());
                    break;
                    
                case "peek":
                    peek(); 
                    System.out.println(peek());
                    break;
                    
                case "change":
                    System.out.println("Input index to change");
                    int index_to_change = userInput.nextInt();
                    
                    System.out.println("Input value to replace with");
                    String replacement = userInput.next();
                    
                    change(index_to_change, replacement);
                    System.out.println(Arrays.toString(this.stack));
                    break;
                    
                case "count":
                    System.out.println(trackStackPosition);
					break;
                    
                case "display":
                    display();
                    break;
                
                case "totalpop":
                    totalPop();
                    break;
                
                default:
                    System.out.println("That is not an option. Please input 'specs' if you need clarification on what you can call.");
                    System.out.println("Remember each command executes only one time.");
                    break;
            }
        }
    }
    
	//________________________________THIS IS THE START OF THE STACK METHODS__________________________________________
    
    public void garbageCollection(){
		//reduces size when size of array is 4x the number of values in it
        if (trackStackPosition == 0){
            stack = new String[1]; // prints null
            stack[0] = null;
        }
        else{
            String[] newStack = new String[trackStackPosition * 2];
            System.arraycopy(stack, 0, newStack, 0, trackStackPosition);
            this.stack = newStack;
            }
        }
    
    public void push(String value) {
        //creates list twice as big when necessary
        if (value.length() != 1) {
            System.out.println("Not valid input. Must be 1 character only.");
            return;
        }
        
        if (trackStackPosition == stack.length) {
            String[] newStack = new String[stack.length * 2];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            this.stack = newStack;
        }
        
        this.stack[trackStackPosition] = value;
        trackStackPosition += 1;
    }
    
    public String pop(){
        if (trackStackPosition > 0) {
           String toPop = this.stack[trackStackPosition - 1];
           System.out.println("Popping String " + toPop + " at index: " + (trackStackPosition - 1));
           
           this.stack[trackStackPosition - 1] = null;
           trackStackPosition -= 1;
           
           return toPop; 
        } else {
            System.out.println("Stack is empty.");
            return "Stack is empty.";
        }
    }
    
    public boolean isEmpty(){
        if (trackStackPosition == 0){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isFull(){
        if (trackStackPosition == stack.length){
            return true;
        } else {
            return false;
        }
    }
    
    public String peek(){
        if (trackStackPosition > 0){
            return this.stack[trackStackPosition - 1];
        } else {
            return "Stack is empty.";
        }
    }
    
    public void change(int index, String replacement){
        if (replacement.length() != 1) {
            System.out.println("Not valid input. Must be 1 character only.");
            return;
        }
        this.stack[index] = replacement;
    }
    
    public void display(){
        if (trackStackPosition > 0) {
            for (int i = trackStackPosition; i >= 0; i--){
                System.out.println(this.stack[i]);
            } 
        } else {
            System.out.println("Can't display. Stack is empty.");
        }
    }
    
    public void totalPop(){
        if (trackStackPosition == 0){
            return;
        } else {
            while (trackStackPosition > 0) {
                pop();
            }
        }
    }
}
