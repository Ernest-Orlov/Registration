package by.jwd.library.controller.command.impl;

import by.jwd.library.controller.command.Command;
import by.jwd.library.controller.command.CommandException;
import by.jwd.library.controller.command.impl.util.QueryCoder;
import by.jwd.library.controller.constant.JSPPath;
import by.jwd.library.controller.constant.RequestAttribute;
import by.jwd.library.controller.constant.RequestParameter;
import by.jwd.library.service.ServiceException;
import by.jwd.library.service.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditMediaForm implements Command {

    private static final Logger logger = LoggerFactory.getLogger(EditMediaForm.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {

            request.setAttribute(RequestAttribute.LAST_COMMAND, QueryCoder.code(request.getQueryString()));

            if (request.getParameter(RequestParameter.EDIT_MEDIA_MSG) != null) {
                request.setAttribute(RequestAttribute.EDIT_MEDIA_MSG, request.getParameter(RequestParameter.EDIT_MEDIA_MSG));
            }

            int mediaId = Integer.parseInt(request.getParameter(RequestParameter.MEDIA_ID));
            by.jwd.library.bean.MediaDetail mediaDetail = ServiceFactory.getInstance().getLibraryService().getMediaDetail(mediaId);
            request.setAttribute(RequestAttribute.MEDIA_DETAIL, mediaDetail);

            request.getRequestDispatcher(JSPPath.EDIT_MEDIA).forward(request, response);
        } catch (ServletException e) {
            logger.error("ServletException in EditMediaForm", e);
            throw new CommandException(e);
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (IOException e) {
            logger.error("IOException in EditMediaForm", e);
            throw new CommandException(e);
        }
    }
}
