package com.minidrive.minidrive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthPageController {

    @GetMapping("/auth")
    public String authPage() {
        return """
            <html>
            <head>
                <title>Mini Drive - Auth</title>
            </head>
            <body>


                <h2>Register</h2>
                <input type="text" id="regUsername" placeholder="Username" />
                <br><br>
                <input type="password" id="regPassword" placeholder="Password" />
                <br><br>
                <button onclick="register()">Register</button>

                <p id="regResult"></p>

                <hr>

                <h2>Login</h2>
                <input type="text" id="loginUsername" placeholder="Username" />
                <br><br>
                <input type="password" id="loginPassword" placeholder="Password" />
                <br><br>
                <button onclick="login()">Login</button>

                <p id="loginResult"></p>

                <script>
                    function register() {
                        fetch("/api/auth/register", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify({
                                username: document.getElementById("regUsername").value,
                                password: document.getElementById("regPassword").value
                            })
                        })
                        .then(res => res.text())
                        .then(data => {
                            document.getElementById("regResult").innerText = data;
                        });
                    }

                    function login() {
                        fetch("/api/auth/login", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify({
                                username: document.getElementById("loginUsername").value,
                                password: document.getElementById("loginPassword").value
                            })
                        })
                        .then(res => {
                            if (res.status === 200 || res.status === 302) {
                                window.location.href = "/";
                            } else {
                                return res.text();
                            }
                        })
                        .then(data => {
                            if (data) {
                                document.getElementById("loginResult").innerText = data;
                            }
                        });
                    }
                    function logout() {
                                        fetch("/api/auth/logout", {
                                            method: "POST"
                                        })
                                        .then(res => res.text())
                                        .then(data => {
                                            document.getElementById("logoutResult").innerText = data;
                                            window.location.href = "/auth"; // back to login/register page
                                        });
                                    }
                
                </script>

            </body>
            </html>
            """;
    }
}
