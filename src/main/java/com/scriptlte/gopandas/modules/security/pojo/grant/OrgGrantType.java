package com.scriptlte.gopandas.modules.security.pojo.grant;


import java.util.ArrayList;
import java.util.List;

public class OrgGrantType {
    private String type;
    private List<OrgGrant> grants = new ArrayList<>();
    private List<OrgGrantType> childTypes = new ArrayList<>();
}
