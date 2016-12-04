Given a doctor this project lists all the similar doctors in prioritized order. 
Assumptions for solving the problem:
Here the priority mean Doctors who have more common features e.g
Doctor1 = {name: "Dr. Midhan", speciality: "Nephrologist", area: "Kathmandu", score: 5}
Doctor2 = {name: "Dr. Chandani", speciality: "Nephrologist", area: "Seattle", score: 4}
Doctor3 = {name: "Dr. Aaron", speciality: "Cardiologist", area: "Kathmandu", score: 1}
Doctor4 = {name: "Dr. Roshni", speciality: "Nephrologist", area: "Vegas", score: 5}
Doctor5 = {name: "Dr. Sally", speciality: "Gynacologist", area: "Seattle", score: 2}
Doctor6 = {name: "Dr. Hem", speciality: "Cardiologist", area: "Alabama", score: 5}
Doctor7 = {name: "Dr. John", speciality: "Nephrologist", area: "Kathmandu", score: 5}

Input: Doctor1
Output: [Doctor7, Doctor1, Doctor4, Doctor3, Doctor6, Doctor2]

I have assumed that a list of Doctor objects with be given initially. And each of the Doctor object have 4 class property, 
they are: speciality, review score and area. Based on this assumption when we pass a Doctor all the list similar doctors 
will be listed based on the priority of similarity.    
