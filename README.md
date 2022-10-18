# HeapSort-Visualizer

Android application for heap sort visualization.

![Untitled (2160 × 1080 px) (4)](https://user-images.githubusercontent.com/90695071/193133453-1a607a2d-78f0-481e-b511-b8c0862f8a6f.png)
![Untitled (2160 × 1080 px) (5)](https://user-images.githubusercontent.com/90695071/193133465-8081cbb8-5b95-4ca5-9bee-fc455440929e.png)\

## Animation video link:
https://youtu.be/N4a2mH3eKzg

## Flow of operation:
1. On launching the application, a splash screen appears which further displays a documentation
from Scalar academy. The documentation covers all the theoretical and programming
perspectives of the Heap and its basic operation. This helps users to grasp the concept better
while having a look at animated video.
2. On getting started, we provide the user three options,

     1. Animated video, which opens a youtube player and users can make use of a variety
        of features such as full screen, modifying speed, preventing ads and more.

        
     2. AR view,On clicking the AR view, it redirects to the camera where you
        will be asked to find a horizontal plane. On clicking on the plane it renders a complete HeapSort video
        in real time which can be rotated 360 degrees and hence provides a flexibilty for the user to learn in his/her own environment.

            
     3. Report, this will open a custom PDF viewer which contains the complete report of heap
      sort.

## Usage of technology:

1. Programming Language: Kotlin and Java.
2. User Interface/Experience: XML
3. AR View: Google’s Sceneform SDK and AR Core.
4. Youtube player: Google developers API console.
5. Platform: Android studio.

## Useful links:
https://developers.google.com/youtube/v3/quickstart/android

https://github.com/google-ar/sceneform-android-sdk

https://developers.google.com/ar/develop

https://www.geeksforgeeks.org/android-tutorial/

## Usage:

1.Clone the project directly into your Android studio IDE using 

      url: https://github.com/mssandeepkamath/HeapSort-Visualizer.git   
      
  or Download the zip file and open it in your IDE.
      
2.Run

## Contribute:

1.Fork this repository

2.clone it into your local pc using

       git clone 'your_cloned_https_url'
         
3. create a new branch using

        git branch dev ; git checkout dev
           
4. Contribute!

5. Stage and commit changes using 

       git add . ; git commit -m "your updates"
           
6. Create pull request in Github GUI for merging the update into GUI/main(upstream).

7. Add original repository for fetch using 

       git remote add upstream https://github.com/mssandeepkamath/HeapSort-Visualizer.git
       
8. Checkout to your origin main and do this for fetching latest changes from original repository.

       git pull upstream main
