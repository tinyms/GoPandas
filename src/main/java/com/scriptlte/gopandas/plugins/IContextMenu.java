package com.scriptlte.gopandas.plugins;

import com.scriptlte.gopandas.ui.ContextMenuItem;

import java.util.List;

public interface IContextMenu {
    public void doInContextMenu(List<ContextMenuItem> contextMenuItemList);
}
