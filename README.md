# Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [opencart](https://github.com/opencart/opencart).

OpenCart is a free open source ecommerce platform for online merchants. OpenCart provides a professional and reliable foundation from which to build a successful online store.
## Open Cart Installation (Windows):
1. Upload all the files and folders to your server from the "Upload" folder. This can be to anywhere of your choice. e.g. /wwwroot/store or /wwwroot

2. Rename config-dist.php to config.php and admin/config-dist.php to admin/config.php

3. Make sure the following folders and files permissions allow Read and Write.
- config.php
- admin/config.php
4. Make sure you have installed a MySQL Database which has a user assigned to it.
- do not use your root username and root password

5. You should be taken to the installer page. Follow the on screen instructions.

6. After successful install, delete the /install/ directory.

## What we tested

We tested the deactivate user account module that allows admin to block users account from the site. We chose to test the following user story:

*User Case:* A user tries to change his account name while the admin block his account. 

*Preconditions:* There is a user and admin accounts registered to open cart.

*Expected outcome:* The username did not change.

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
In conclusion the system work as expected, That is the user changes on his account details were not saved after he got blocked by the admin. You can also see the results in the README files at Provengo/Cucumber dircotory.
 
