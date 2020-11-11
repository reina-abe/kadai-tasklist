package controllers;
//データベースから複数のメッセージ情報を取得して一覧表示する

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import util.DBUtil;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //getAllTasks を createNamedQuery メソッドの引数に指定して、データベースへの問い合わせを実行、リスト形式で取得
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();

        em.close();

        //データベースから取得したタスク一覧をリクエストスコープにセット
        request.setAttribute("tasks", tasks);

        //ビューとして /WEB-INF/views/tasks/index.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }
}