# Video + Screenshots
[Video](https://drive.google.com/file/d/1H0CoMtLYncs2nRJktXSqdrkQRsdN6LJO/view?usp=drive_link)

![image](https://github.com/amberhasan/20230712-AmberHasan-NYCSchools/assets/12038406/e5efa367-388f-4b14-88b5-cb8db03b119a)

# Features
1. Fetching data from two different APIs (SCHOOL_DATA_API and DETAIL_DATA_API) using Volley library.
2. Parsing JSON responses from the APIs to extract relevant data.
3. Displaying a progress dialog while fetching data.
4. Handling network errors and displaying error messages.
5. Creating School and Detail model classes to represent the fetched data.
6. Implementing a custom SchoolAdapter to display the list of schools in a ListView.
7. Handling item click events in the ListView to open a detail activity (DetailActivity) with corresponding data.
8. Passing data between activities using Intent extras.
9. Implementing a method (findDetailByDBN) to find the corresponding Detail object based on the school's DBN value.
10. Setting the adapter for the ListView to display the list of schools.


# Instructions
Coding Challenge: NYC Schools

GOAL : Verify candidate can provide a technical solution and follow instructions

REQUIREMENTS :

These requirements are rather high-level and vague. If details are omitted, it is because we will be happy with any of a wide variety of solutions. Don't worry about finding "the" solution. Feel free to be creative with the requirements. Your goal is to impress (but do so with clean code).

Create a browser based or native app to provide information on NYC High schools.

Display a list of NYC High Schools.
Get your data here: https://data.cityofnewyork.us/Education/DOE-High-School-Directory-2017/s3k6-pzi2
Selecting a school should show additional information about the school
Display all the SAT scores - include Math, Reading and Writing.
SAT data here: https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4
It is up to you to decide what additional information to display
When creating a name for your project, please use the following naming convention:

YYYYMMDD-[First&LastName]-NYCSchools (Example: 20180101-DanielleBordner-NYCSchools)

In order to prevent you from running down rabbit holes that are less important to us, try to prioritize the following:

What is Important

Proper function – requirements met.
Well constructed, easy-to-follow, commented code (especially comment hacks or workarounds made in the interest of expediency (i.e. // given more time I would prefer to wrap this in a blah blah blah pattern blah blah )).
Proper separation of concerns and best-practice coding patterns.
Defensive code that graciously handles unexpected edge cases.
What is Less Important

Demonstrating technologies or techniques you are not already familiar with.
Bonus Points!

Unit Tests
Additional functionality – whatever you see fit.
iOS:

For applications that include CocoaPods with their project code, having the Pods included in the code commits as source is recommended. (Even though it goes against the CocoaPods general rules).
Be sure to use safe area insets.
Make sure your app is compatible with iPhone X.
Android:

Make sure you are correctly handing any necessary permissions.
Please make sure you are using Java. If you want to demonstrate the use of Kotlin, we'd rather you use a combination of both.
As mentioned, you are not expected to function in a vacuum. Use all the online resources you can find, and please do contact us with questions or for interim feedback if you desire.
