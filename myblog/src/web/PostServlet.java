package web;


import bean.Post;
import dao.PostDAO;
import vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.getPostById(id);
            resp.getWriter().print(ResultVO.ok(post).toJSON());
        } catch (Exception e) {
            e.printStackTrace();
            String err = ResultVO.err(111, e.getLocalizedMessage()).toJSON();
            resp.getWriter().print(err);
        }
    }
}
