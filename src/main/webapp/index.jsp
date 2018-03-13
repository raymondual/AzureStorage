<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
       <title>Upload File Request Page</title>
       <script src="javascript/jquery-3.3.1.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    </head>
    <body>
        <h2>Upload Test</h2>
        <form method="POST" action="uploadFile" enctype="multipart/form-data">
           File to upload: <input type="file" name="file">
           <input type="submit" value="Upload">
        </form>
        
        <h2>Download Test</h2>
        <form method="POST" action="downloadFile">
           File to upload: <input type="text" name="fileName" value="My.pdf">
           <input type="submit" value="Download">
        </form>
    </body>
</html>
