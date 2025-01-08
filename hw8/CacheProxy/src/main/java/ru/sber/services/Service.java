package ru.sber.services;

import ru.sber.annotations.Cache;

import java.util.Date;
import java.util.List;

import static ru.sber.enums.CacheTypeEnum.FILE;
import static ru.sber.enums.CacheTypeEnum.IN_MEMORY;

public interface Service {

    @Cache(cacheType = IN_MEMORY, listList = 100_000)
    List<String> work(String item);

    @Cache(cacheType = FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);
}
