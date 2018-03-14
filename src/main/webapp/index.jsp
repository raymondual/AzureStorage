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
           File to upload: <input type="file" name="file" />
           <input type="submit" value="Upload" />
        </form>
        
        <h2>Download Test</h2>
        <form method="POST" action="downloadFile">
           Download file name: <input type="text" name="fileName" value="My.pdf" />
           <input type="submit" value="Download" />
        </form>
        
        <h2>Delete Test</h2>
        <form method="POST" action="deleteFile">
           Delete file name: <input type="text" name="fileName" value="My.pdf" readonly />
           <input type="hidden" name="id" value="6" />
           <input type="submit" value="Delete" />
        </form>
        
        <h2>Query Test</h2>
        <form method="POST" action="listFile">
           <input type="submit" value="Search" />
        </form>
    </body>
</html>
