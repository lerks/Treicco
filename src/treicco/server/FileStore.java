package treicco.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class FileStore extends HttpServlet {

	private static final long serialVersionUID = 0L;

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("file");

		if (blobKey == null) {
			res.sendRedirect("/");
		} else {
			String name = req.getParameter("name");
			String parent = req.getParameter("parent");

			Image i = new Image();
			i.create(parent, name, blobKey.getKeyString());

			res.sendRedirect("/filestore?id=" + blobKey.getKeyString());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BlobKey blobKey = new BlobKey(req.getParameter("id"));
		blobstoreService.serve(blobKey, res);
	}
}
