import controller.ItemController;
import models.Item;
import service.ItemService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns ="/tes")

public class MyServlet extends HttpServlet {
    private ItemController itemController = new ItemController();


    @Override //получить информацию
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String str = req.getParameter("id");
        System.out.println(str);

        try {
            Long id = Long.valueOf(str);
            itemController.findById(id);
            resp.getWriter().println("DOGET id=" + id+" DONE");
            resp.getWriter().println(itemController.findById(id));

        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong format !!!!!");

        } catch (IllegalAccessException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override //удалить информацию
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String str = req.getParameter("id");
        try {
            Long id = Long.valueOf(str);
            itemController.delet(id);
            resp.getWriter().println("DELETE ID= "+ id +" DONE");

        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong format !!!!!");

        } catch (IllegalAccessException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override //сохранить в базу данных save  большие данные
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        java.sql.Date sqlDate = new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis());
        Item itemTemp = parsItem(req);
        itemTemp.setDateCreated(sqlDate);
        try {
            itemController.save(itemTemp);
            resp.getWriter().println(itemTemp.toString());
            resp.getWriter().println("doSaveDone");

        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong format !!!!!");
        } catch (IllegalAccessException e) {
            resp.getWriter().println(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }


    @Override //обновить в базу данных 
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        java.sql.Date sqlDate = new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis());
        Item itemTemp = parsItem(req);
        itemTemp.setLastUpdatedDate(sqlDate);
        try {
            itemController.update(itemTemp);
            resp.getWriter().println(itemTemp.toString());
            resp.getWriter().println("doPutDone");

        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong format !!!!!");
        } catch (IllegalAccessException e) {
            resp.getWriter().println(e.getMessage());
        }

    }

    private static Item parsItem(HttpServletRequest req) {
        String strId = req.getParameter("id");
        Long id = Long.valueOf(strId);
        String strName = req.getParameter("name");
        String strDescription = req.getParameter("description");
        Item item = new Item();
        item.setId(id);
        item.setName(strName);
        item.setDescription(strDescription);
        return item;
    }

}



