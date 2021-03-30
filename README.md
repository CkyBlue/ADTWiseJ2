This project is, in summary, various tools my friends and I attempted implementing to make it easier to grasp the inner-workings of complicated algorithms that we encountered in Computer-Science classes. Since this was as much a learning experience as it was a project endeavor for us, we started with attempting to re-invent the wheel for common code-editor features like executing code one line at a time, and a screen for monitoring variables before trying to come up with features to complement them. Here's how we tried to accomplish this:

An algorithm gets divided into logical sections (for e.g, the statements of code handling cleanup after modifying some data making up one section, the statements that actually do the modification making another)

![exec_highlighting](https://user-images.githubusercontent.com/34099661/112932300-647edb80-913d-11eb-9428-3439337d89b7.png)
![exec_log](https://user-images.githubusercontent.com/34099661/112932354-7791ab80-913d-11eb-9471-3ddbe5acf24d.png)

Before executing any section of code, we look at some logs (explanatory texts) generated to explain the purpose of the code that we will encounter in that section. (for e.g, what does setting the variable 'Free_Pointer' to an initial value 0 achieve in the grand scheme of things for an algorithm)

We then execute that section of code and have a look at the data stored in variables to see how they were affected by the execution of the code. For variables that can be interpretted as being a part of a logical entity such as a 'Tree' or 'Chain' of variables, we also see the changes reflected in the form of Tree/Chain diagrams. Consider the following data for example.

![data](https://user-images.githubusercontent.com/34099661/112932399-8b3d1200-913d-11eb-9341-ee238de4a040.png)

This is the data stored by a program emulating a Queue. The alphabets I, J, K, L, M, N & O were inserted into this Queue in alphabetical order (the data in the 'Item' column) but as you can see in the diagram, the order appears jumbled up (the numbers at the left-most is the order in which they are internally stored). However, consider the chain diagram representation of this data now.

![node-link](https://user-images.githubusercontent.com/34099661/112932433-9ee87880-913d-11eb-83c0-396f3a9d6c59.png)

If you look at this alongside the chain representation of this data, you'll see that the values in the right corner allow matching up the data items in the order they are meant to be percieved (the alphabets show up in the correct order in the chain even though the chain and the above table represent the same data). The reason the internal representation of the data ended up so distorted was because of subtle bits and pieces of code scattered in various parts of the program that nonetheless ensure that the data does indeed behave as a Queue when percieved in the intended manner (as a chain). The logs that accompany those subtle bits and pieces try to inform us of these kinds of far reaching consequences of things we do there, consequences that might not be easily apparent at first.

Video Explanation: https://youtu.be/uinBbelOSWk
