package com.minidrive.minidrive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
            <html>
            <head>
                <title>Mini Drive</title>
            </head>
            <body>
<!-- logout -->
                <h2>Mini Drive</h2>
                <form action="/api/auth/logout" method="post">
                        <button type="submit">Logout</button>
                    </form>
                
                    <hr>
                

                <!-- UPLOAD -->
                <form method="post"
                      action="/api/upload"
                      enctype="multipart/form-data">

                    <input type="file" name="file" required />
                    <br><br>
                    <button type="submit">Upload</button>
                </form>

                <hr>

                <!-- LIST FILES -->
                <button onclick="listFiles()">List Files</button>

                <table border="1" cellpadding="8" style="margin-top:10px;">
                    <thead>
                        <tr>
                            <th>File Name</th>
                            <th>Uploaded At</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="fileTable"></tbody>
                </table>

                <script>
                    function formatDateTime(dateTimeStr) {
                        const date = new Date(dateTimeStr);

                        const day = String(date.getDate()).padStart(2, '0');
                        const month = String(date.getMonth() + 1).padStart(2, '0');
                        const year = date.getFullYear();

                        const hours = String(date.getHours()).padStart(2, '0');
                        const minutes = String(date.getMinutes()).padStart(2, '0');
                        const seconds = String(date.getSeconds()).padStart(2, '0');

                        return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
                    }

                    function listFiles() {
                        fetch("/api/files")
                            .then(res => res.json())
                            .then(data => {
                                const table = document.getElementById("fileTable");
                                table.innerHTML = "";

                                data.data.forEach(file => {
                                    const row = document.createElement("tr");

                                    row.innerHTML = `
                                        <td>${file.name}</td>
                                        <td>${formatDateTime(file.uploadedAt)}</td>
                                        <td>
                                            <a href="/api/download/${file.name}">Download</a>
                                            &nbsp;
                                            <button onclick="deleteFile('${file.name}')">Delete</button>
                                            &nbsp;
                                            <button onclick="renameFile('${file.name}')">Rename</button>
                                        </td>
                                    `;

                                    table.appendChild(row);
                                });
                            });
                    }

                    function deleteFile(name) {
                        fetch(`/api/delete/${name}`, {
                            method: "DELETE"
                        }).then(() => listFiles());
                    }

                    function renameFile(oldName) {
                        const newName = prompt("Enter new name:", oldName);

                        if (!newName || newName === oldName) return;

                        fetch(`/api/rename?oldName=${encodeURIComponent(oldName)}&newName=${encodeURIComponent(newName)}`, {
                            method: "PUT"
                        }).then(() => listFiles());
                    }
                </script>

            </body>
            </html>
            """;
    }
}
