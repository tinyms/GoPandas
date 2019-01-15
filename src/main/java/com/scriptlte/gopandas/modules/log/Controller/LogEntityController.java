package com.scriptlte.gopandas.modules.log.Controller;

import com.scriptlte.gopandas.modules.log.annotation.LogRecord;
import com.scriptlte.gopandas.modules.log.config.LogRecordConstant;
import com.scriptlte.gopandas.modules.log.service.LogEntityService;
import com.scriptlte.gopandas.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.geom.RectangularShape;

@Controller
@RequestMapping("/log")
public class LogEntityController {

    @Autowired
    private LogEntityService logService;

    @LogRecord(operaDescription = "查询所有操作日志", operaFunctionName = "操作日志查询", operaModule = LogRecordConstant.LOGRECORD_MODULE_LOG)
    @RequestMapping("{module}")
    public ResponseMessage getLogsByModule(@PathVariable(name = "module") String module) {
        return ResponseMessage.GetSuccessMessage(logService.getLogsByModule(module));
    }
}
