# IP-Project

Create a simple application for online fitness, which provides users with the ability to search for fitness programs, keep an activity journal, and receive exercise suggestions. The system consists of several parts described below:

Fitness Online Application:
This is the central application of the system used to provide and search for online fitness programs. The application allows users to view and participate in various fitness programs. Each program has a name, description, category (e.g., cardio, strength, flexibility, HIIT), basic common properties (price, difficulty level, duration), location (e.g., online, gym, park), images, instructor information, and contact details. Specific attributes are added for each category (e.g., for cardio - type of activity, for strength - type of equipment or bodyweight). All possible categories and attributes are defined in the administrative application. Filtering and searching by these criteria are required. It is not allowed to display all programs simultaneously; instead, some form of pagination (classic pagination or virtual scroll) needs to be supported. Programs are displayed as cards, with mandatory display of name, image, and price. Clicking on a card opens details on a new page showing all information. Users can ask questions, and all conversation for a particular program is displayed to all users as comments.

Users without an account can search for programs, view details, but cannot ask questions or participate. An account is created on the registration form, where users enter their name, surname, city, username, password, avatar (optional), and email. After filling out the form, a confirmation link is sent to the email for account activation, leading the user to the registration endpoint. Optionally, provide a Captcha service to confirm that it is a real user, not a bot, such as https://javalite.io/captcha. When the account is successfully created and activated, the user accesses the home page, where all functionalities are available in the form of a menu. If the account is created but not activated, when the user logs into the system, they receive an activation form (entering the correct username and password), and then a link is generated again and sent to the email. Signed-in users can change their data (except username) on a separate page. In addition, they can view their previous program participations, purchases, etc.

Participation is done by users choosing a payment method (card, PayPal, in-person), and the program is recorded as participated. Detailed processing of the payment method is not required, only basic usage (e.g., entering a card number). Participation implies going to the location if it's an in-person fitness program or watching a YouTube video for online fitness programs.

Each user can create a new fitness program that will be available for search by other users. Additionally, users can view their programs (active and completed) and delete any of their programs.

Users with an account can send messages to advisors for program selection from a form located somewhere in the application. When contacting, information about the user and the message content is saved. Also, users can communicate with each other via messages.

The home page of the application displays an RSS feed with the latest news and information from the fitness world by consuming the RSS feed https://feeds.feedburner.com/AceFitFacts. Additionally, the application consumes an API (https://api-ninjas.com/api/exercises) to provide daily suggestions of 10 exercises with instructions to registered users. Exercises are displayed with a name, instructions, and level.

A registered user can keep their activity journal, track exercise results and progress. The user can enter information about the type of exercises, duration, intensity, and results. The application provides users with a graphical representation of progress, including weight loss, over a certain period. The user can download their activity journal as a PDF document.

Users are offered the option to subscribe to a specific category. Subscribed users receive daily emails with new programs created for that category.

The application must have a uniform look across all pages and must be responsive. Angular and SpringBoot are used for development, and the choice of database is optional. Ready-made libraries such as Bootstrap or Material are allowed. All functionalities of the SpringBoot application are available via RESTful services.

Administrative Application:
Access to the administrative application requires an account, which is opened directly in the database (name, surname, username, password). The home page contains a login form. If the login is successful, a page with a menu containing options is displayed:
Categories: Allows managing (CRUD) data about categories and specific categories of fitness programs.
Users: Allows managing (CRUD) data about users of the fitness online application.
Statistics: Allows viewing logs of the fitness backend application.

The implementation of the administrative application must be done using JSP M2. The use of ready-made libraries for user interface design is allowed.

Consultation Application:
Access to the consultation application requires an account that must be opened by the administrator through the administrative part of the application. This account is different from the accounts for the administrative or fitness online application. If the login is successful, a page is opened where the advisor can view all received messages. The table displays all unread messages, and opening one changes its status to read. Advisors respond to messages by sending an email. Besides text, it is necessary to allow the advisor to send a document with additional descriptions or images. The advisor can search all messages by content.

The consultation application is implemented using JSP.
