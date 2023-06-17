#  STC File System


### Goal

The Goal is to implement system for managing upload/download files

### Overview

System should be managed as a tree data structure, space as the parent then folders and files as
children
This system provide many endpoint:
- Create Space
- Create Folder
- Create File
- Get File Metadata
- Download File 

## to run the application:
Just execute the following file **runApp.sh** file. and the application initlize after the startup group **admin** 
in table **Permission_Group** and **two user_email** in table **Permissions**  and these two users assigned to **admin** group
   * **testview@gmail.com** with  **View** permission 
   * **testedit@gmail.com** with  **Edit** permission 







Change default port value in application.properties

