# CSC207 Group 61 <Project Name>

<!--Basic introduction to project as a whole-->
  
## Existing user logins for testing purposes
  **Admin User Login:**  
  **Username:** "Admin" **Password:** "Password"  
  **Warehouse User Login:**  
  **Username:** "Warehouse" **Password:** "Password"  
  **Store User Login:**  
    **Username:** "Store" **Password:** "Password"
    
## Existing products
  **Name:** "Strawberries" **UPC:** 4001 **Price** 2  
  
## Existing Facilities
  **Name:** "TestStore" **Type:** "Store"  
  **Name:** "TestStore2" **Type:** "Store"  
  **Name:** "TestFacility" **Type:** "Warehouse"  
  **Name:** "TestFacility2 **Type:** "Warehouse"  

## CreateNewUser
This feature allows **ADMIN USERS** to create new facility **(STORE or WAREHOUSE)** users through the admin main menu. To avoid confusion, this feature is **not available** in the **login interface**. This is because **ONLY ADMINS** are permitted to create new (facility) users, as general users should not have the permission to do so. Through the admin main menu interface, the admin user will be able to select the "New User" button which will send them to the Create New User interface. Here, The admin user may create either a store or warehouse user with two requirements:  
    **1.** The username must be unique. If there is an existing user in the database with the same username, the new user is not created and an error informs the admin user that there is already an existing user with that username.  
    **2.** The password must be longer than 4 characters. If the password is shorter, an error will inform the admin that the password is too short.
