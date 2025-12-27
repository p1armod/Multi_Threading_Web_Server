Multi-Threading Web Server (Java)

This project demonstrates three different approaches to building a simple Java-based web server to handle client requests:
1.	Single-Threaded Server
2.	Multi-Threaded Server (Thread-per-Request)
3.	Thread Pool–Based Server

The goal is to understand how concurrency models impact performance, scalability, and resource utilization when serving multiple clients.

⸻

Multi_Threading_Web_Server/
├── SingleThreaded/
│   └── Server.java
│
├── MultiThreaded/
│   ├── Server.java
│   └── Executor.java
│
├── ThreadPool/
│   ├── Server.java
│   └── Executor.java
│
└── .git/

⸻

🚀 Implementations Overview

1️⃣ Single-Threaded Server
•	Handles one client request at a time.
•	Each request blocks the server until processing is complete.
•	Simple to implement but not scalable.

Use Case: Learning basics of socket programming.

⸻

2️⃣ Multi-Threaded Server (Thread-per-Request)
•	Creates a new thread for every incoming client request.
•	Allows concurrent request handling.
•	Can lead to high memory usage and performance degradation under heavy load due to excessive thread creation.

Use Case: Moderate traffic with short-lived requests.

⸻

3️⃣ Thread Pool Server (Recommended)
•	Uses a fixed-size thread pool to process requests.
•	Threads are reused, reducing overhead.
•	Provides better performance, stability, and resource control.

Use Case: Production-grade servers and high-concurrency systems.

⸻

⚙️ Technologies Used
•	Java
•	Java Sockets (ServerSocket, Socket)
•	Java Concurrency (Thread, ExecutorService)

⸻

▶️ How to Run
1.	Navigate to the desired implementation directory:

cd SingleThreaded
# or MultiThreaded / ThreadPool


	2.	Compile the server:

javac Server.java


	3.	Run the server:

java Server


	4.	Open multiple browser tabs or use tools like curl or ab to test concurrency.

⸻

📊 Key Learnings
•	Difference between sequential and concurrent request handling
•	Limitations of thread-per-request models
•	Advantages of thread pooling in real-world systems
•	Core concepts used in web servers and backend systems

⸻

📌 Future Improvements
•	Add HTTP request parsing
•	Support multiple routes
•	Graceful shutdown handling
•	Metrics for response time and throughput

⸻

🧑‍💻 Author

Parmod Kumar
MCA | Backend & Distributed Systems Enthusiast

⸻

⭐ If you find this project helpful, feel free to star the repository!





