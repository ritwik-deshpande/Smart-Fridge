# CS437-smart-inventory

<a name="br1"></a> 

**CS437: Internet of Things**

**Final Project Report**

**Name: Harshit Agarwal, Ojasvi Agarwal, Ritwik Deshpande, Yeshwanth Sripathy**

**NetID: ha46, ojasvia2, ritwikd2, ys58**

**Code Link : <https://gitlab.engr.illinois.edu/ha46/picar-4wd/-/tree/master/final_project>**

**Video Link: [Final_Project.mov](https://drive.google.com/file/d/1zQUEHMvZHOMTSUjvm-YKyMQJGT75P-Y0/view?usp=sharing)**

**Motivation:**

**a) Importance of the problem:**

Most of us in our family in recent generations are very busy with their jobs. With the exhaustive

schedule and workloads we tend to miss or ignore basic household chores. One of the basic

household chores includes managing the food inventory, especially in adverse weather

conditions where stocking up food becomes vital. Statistically we all know that in a day we

spend at least 5-6 hours on our phone, whether it be checking email, calls, messages or other

important notifications. Hence through our solution we aim to provide a real time system to track

our food inventory and capitalize on that data to show recipes and other information on our

mobile device. As bachelors we have faced this situation many times where we are either out of

stock or don’t know about the various dishes we can create with the existing food items. Thus to

reduce wastage of food and enhance our resourcefulness as individuals was the primary

motivation behind choosing this project.

From the technical aspect, firstly using our knowledge we gained through the course we are

planning to deploy a Pi Camera with Raspberry Pi attached at the door of the fridge as a smart

device for object detection. We are mainly looking to incorporate the ideas learnt from edge

computing, edge networking and cloud infrastructure learnt during our IoT labs. We are also

looking to expand our domain knowledge across Machine learning (where we are planning to

Object detection and identification) as well as dynamic processing of requests and sending

appropriate notifications to the App to keep the real time data in sync(where we are planning to

use Sockets/gRPC for communications). Lastly we want to provide a seamless user experience

through a mobile application which gives them the accurate and timely details of their inventory

along with suggesting unique and interesting food recipes using them.

**b) Pitching the Idea**

The main intent behind our ideas is to give people access to their fridge anytime, anywhere.

Using an Internet of Things (IOT) device such as raspberry pi we are turning our typical fridge

into one that is smart and connected. Using machine learning-based image recognition

software, our application tracks the movement of food in and out of our fridge, and uses this



<a name="br2"></a> 

information to deliver expiration date reminders, and recipe recommendations right to your

phone.

**c) Background Research**

The concept of tying home appliances to the internet (Internet of Things) had gained popularity

and was regarded as the upcoming big thing by the late 1990s and early 2000s. The Internet

Digital DIOS, the first internet-connected refrigerator, was introduced by LG in June 2000. The

reason why this refrigerator failed to find a market was that consumers thought it was overpriced

($20,000) and superfluous. This shows the importance of making sure the IoT-based solutions

are affordable so that they can have a much bigger customer base and make the lives easier for

a larger target audience. [1] gave us a more detailed overview to understand the practical

challenges people facing while building, using and running a smart fridge such as lack of 1) user

experience focus, 2) low-intrusion object recognition and 3) automatic item position detection.



<a name="br3"></a> 

**Technical Approach:**

**a) Overall architecture**

Image above describes the overall architecture that our application is going to follow. First when

the object will be placed inside the fridge, the object detection model running on raspberry pi will

determine the direction in which the item is going in order to determine whether an object is

being added or removed. Based on that, it will detect the object and send the relevant HTTP

POST request to the flask server. The server after receiving the data will store the same in a

NOSQL database called mongodb atlas and update the android app regarding that an item has

been added or removed from the fridge along with relevant attributes such as name, expiry, food

image, and date added. A button would also be present on the android app screen which will

take the items detected in order to get recipe recommendations using an API called

spoonacular API. **The highlight of our project is that as part of the food image we are**

**sending to the android app, we will send the same image that pi has captured in the**

**fridge.**



<a name="br4"></a> 

**b) Data flow through the system**

The schema of the data object that we are sending across the system has 4 attributes given

below (Name : DataType) :

\-

\-

\-

\-

Name : String

ExpiryDate: Integer (Number of seconds)

dateAdded : epoch

ItemImage: base64 encoded string

The pi sends an HTTP POST request to the cloud backend which hosts our application server

and talks to the database. The data sent as part of the request gets stored in the mongoDB

database in JSON format since it is a NOSQL database. The cloud backend will in turn talk to

the android application using TCP/IP socket connections which would ensure bi-directional

communication and data is sent as strings. Another aspect where data flow communication is

happening is when the mobile application fetches the recipe recommendations using an

open-source API called spoonacular and therefore here mobile application to internet and

internet to mobile application communication takes place. **The X-factor of our data flow**

**communication is that fact that we are capturing live image from the pi, encoding it as a**

**base64 string, sending it across all the way to the mobile application where is decoding**

**the base64 string and displaying the live image captured by pi to the user on the android**

**app.**

**Implementation Details:**

**a) Software Packages used**

Here is the list of software packages that have been used:

1\. Flask

Flask is a web framework, it’s a Python module that lets us develop web applications easily. We

use flask as a python server here since it’s very pythonic, has good readability and lets us

handle different types of communication with various components in a seamless manner.

2\. Cv2 and YOLO model

cv2 is the module import name for opencv-python which is a library of Python bindings designed

to solve computer vision problems. We have paired the cv2 package with the YOLO object

detection machine learning model in order to detect the different food items entering and exiting

the fridge. Some of the reasons why we chose YOLO for real-time detection is because it

provides good speed, detection accuracy, Good generalization as well as it’ open source to use.



<a name="br5"></a> 

3\. AndroidSDK

The Android SDK is a software development kit that includes a comprehensive set of

development tools. These include a debugger, libraries, a handset emulator based on QEMU,

documentation, sample code, and tutorials. We chose android since the majority of the users in

our group were android users and they were interested in exploring android development.

4\. Spoonacular API

Spoonacular is a food management system that combines dining out, eating store-bought food,

and cooking at home to help us find and organize the restaurants, products, and recipes that fit

our diet and can help us reach our nutrition goals. We chose spoonacular API because we felt it

was built using a very comprehensive dataset having 2600+ ingredients, 5000+ recipes, 600K

products and 115K+ menu items which would have increased our chances of getting good

recipe recommendations based on the items we send.

5\. PyMongo

PyMongo is a Python distribution containing tools for working with MongoDB, and is the

recommended way to work with MongoDB from Python.

**b) Hardware Modules used**

1\. Raspberry pi

We decided to use raspberry pi as the compute hardware to perform the object detection

capability since its compact size helped us in placing it in the fridge. We also built upon our

learning from Lab 1 and Lab 2 where we performed object detection using the pi on a car and

that gave us the confidence to use it for our final project.

2\. piCam

The pi Camera module is a camera that can be used to take pictures and high definition video.

Raspberry Pi Board has CSI (Camera Serial Interface) interface to which we can attach the

PiCamera module directly. We chose to work with piCam since we felt it would have the best

compatibility while working with pi and we felt it gave very good results for live object detection

in our previous labs.



<a name="br6"></a> 

**c) Research Papers we referred**

Following research papers were referred as part of the project:

1\. Cloudfridge: a testbed for smart fridge interactions [1]

This research paper describes the design and implementation of a testbed called

Cloudfridge for evaluating smart fridge interactions.The authors argue that smart fridges

have the potential to provide many useful services, such as inventory management and

recipe suggestions, but their development is hindered by the lack of standardized

testbeds. Cloudfridge aims to address this issue by providing a flexible and extensible

platform for evaluating smart fridge applications.Cloudfridge consists of a physical fridge

equipped with various sensors and actuators, as well as a cloud-based software platform

for managing and analyzing data. The authors describe several use cases for

Cloudfridge, such as tracking food expiration dates and suggesting recipes based on

available ingredients.The experiments conducted by the authors demonstrate the

feasibility of Cloudfridge as a testbed for smart fridge interactions and highlight the

potential benefits of smart fridges for both consumers and food industry stakeholders.

This paper acted as an inspiration and got us interested in implementing a similar

product so that it could give us opportunities to observe and relate to the findings in the

research paper.

2\. You Only Look Once: Unified, Real-Time Object Detection [2]

This research paper proposes a unified framework for object detection in images called

YOLO (You Only Look Once). The authors argue that existing object detection methods

rely on multiple stages, such as region proposal and classification, which makes them

computationally expensive and slow. YOLO, on the other hand, performs object

detection in a single stage by dividing the input image into a grid and predicting

bounding boxes and class probabilities for each grid cell.The authors also introduce a

new loss function for training YOLO that balances between localization error and

classification error.The experiments conducted by the authors show that YOLO

outperforms state-of-the-art object detection methods in terms of both accuracy and

speed, making it a suitable choice for real-time applications such as autonomous driving

and robotics.We referred this paper to order to better understand what all tweaks can be

made with respect to hyperparameters in order to make sure that our food objects gets

classified by Raspberry Pi.



<a name="br7"></a> 

**d) Data structure and Protocol choices**

1\. Data structure choices

Our team decided to use JSON format to store data in the project since JSON gave the

flexibility to change the scheme if needed. Another reason was because it is easy to

parse using a wide range of programming languages and platforms which came in handy

for us when a python-based flask server would exchange data with a java-based android

application.

2\. Protocol choices

For sending data from pi to cloud backend (flask server running on EC2), we used HTTP

request to send the data since in this case our intent was to only push data, whereas,

when data exchange is happening between cloud and mobile application in that case we

decided to use socket connections so that we can bi-directional communication. The

reason we preferred bi-directional communication was because it made the transfer

happen in a more efficient way. Now instead of making the mobile application keep

polling and checking where new data has come on the server side, the server instead

sends a signal to the mobile application that “hey! Take a pull” and then the mobile

application would pull and get the updated data and show it in the mobile application.

**e) External materials that were used**

1\. <https://www.mongodb.com/docs/>

2\. <https://pymongo.readthedocs.io/en/stable/>

3\. <https://flask.palletsprojects.com/en/2.3.x/>

4\. <https://developer.android.com/docs>

5\. <https://www.datacamp.com/blog/yolo-object-detection-explained>

6\. <https://spoonacular.com/food-api>

**Results**

**a) How did things turn out**

Our YOLO based ML Model running on raspberry pi was able to detect different food items such

as apple, banana and donut. We were successful in adding and removing these food items to

the fridge which updated the list we showed on the android application as well. Our application

was also capable of calling an api through the mobile application which would take the updated

list of items and update the recipe recommendations based on it. For instance, initially, only

eggs and milk were present in the app and the app would show recipe recommendations only

based on eggs and milk. Now, as soon as we add apples, the recipe recommendations would



<a name="br8"></a> 

get updated and we would see recipes such as apple smoothie, apple pancakes etc. Finally, our

team also worked with firebase and added notifications as part of this project where whenever a

food item would expire within 1 day, the backend server would trigger a notification to the app

and it would pop on the mobile screen with a message such as “Eggs are expiring soon”.

Screenshot showing all three items apple, donut and banana added which were shown in the

demonstration as well:

Screenshot showing recipe recommendation when the upper right corner is clicked:



<a name="br9"></a> 

Screenshot showcasing that eggs are expiring soon:



<a name="br10"></a> 

**b) Main takeaways (why should you care about our results)**

Though there is a long way to go, if further improvements are made and our application gets

refined further, it can make a significant difference in the lives of the young generation by

making their lives easier through real-time tracking of the ingredients present in their fridge. For

example, let's say a guy in his 20’s is working late in his office and wants to know what all the

stuff is there is his fridge that he can use to cook. The user just needs to open our app to check



<a name="br11"></a> 

the list of ingredients. Also, we are helping the user even further by providing a list of quick

recipes that the user can make using the exact ingredients present in his fridge. Finally, we are

also letting the user know when his things are expiring so that he can consume them in a faster

manner and lead to less wastage of food and money.

**c) What did not go right in the project**

Ideally, we also wanted to show the rack at which the object is placed in the fridge and

showcase that information in the android app as well however the ML model we developed was

not capable enough to be able to judge the height differences between racks through the pi cam

and be able to determine the rack at which the object is present.

**d) What was hard**

Our team faced challenges while establishing the real-time communication between the server

and the application through socket connections in order to ensure that the server doesn’t get

overloaded by using the standard practice of making the app keep polling for long periods of

time to the server to get updated data. This was a system design tradeoff we made to ensure

we don’t face bandwidth constraints later and the team felt it was hard to design and implement.

**e) What lessons did we learn**

Following are the lessons we learned:

1\. Machine learning is hard. As rosy as may seem, running an actual machine learning

model with the right level parameters which has the accuracy good enough to be able to

detect real objects was a fun and huge learning experience.

2\. The realization that the real world works very differently from the ideal world. For

instance, we observed that in the real world, we had to keep our object items very slowly

in the fridge in order to ensure that the pi cam is able to detect the same, whereas, in the

ideal world, the expectation would be that ML model would be able to detect quickly

placed or unaligned food items as well.

3\. The project provided us learning in various aspects such as networking (through socket

connections), image encoding (to be able to send live image captured by pi cam to the

mobile application) and notification service (firebase being used by server to push a

notification to the mobile app).

**References:**

[1] Sandholm, Thomas, Dongman Lee, Bjorn Tegelund, Seonyeong Han, Byoungheon Shin, and

Byoungoh Kim. "Cloudfridge: a testbed for smart fridge interactions." *arXiv preprint arXiv:1401.0585*

(2014).



<a name="br12"></a> 

[2] Redmon, Joseph, Santosh Divvala, Ross Girshick, and Ali Farhadi. "You only look once: Unified,

real-time object detection." In Proceedings of the IEEE conference on computer vision and pattern

recognition, pp. 779-788. 2016.

