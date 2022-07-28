package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    //gets list of cards
    @RequestMapping(path = "/cards", method = RequestMethod.GET)
    public List<CatCard> catCardList() {
        return this.catCardDao.list();
    }

    //gets card from id
    @RequestMapping(path = "/cards/{id}", method = RequestMethod.GET)
    public CatCard getCatCard(@PathVariable int id) {
        return this.catCardDao.get(id);
    }

    //random
    @RequestMapping(path = "/cards/random", method = RequestMethod.GET)
    public CatCard randomCatCard(){
        CatCard catCard = new CatCard();
        catCard.setCatFact(catFactService.getFact());
        catCard.setImgUrl(catPicService.getPic());

        return catCard;
    }

    //saves card
    @PostMapping("/cards")
    public boolean saveToCard(@RequestBody CatCard catCard) {
        return this.catCardDao.save(catCard);
    }

    //updates card
    @RequestMapping(path = "/cards/{id}", method = RequestMethod.PUT)
    public boolean updateCard(@Valid @RequestBody CatCard catCard, @PathVariable int id) {
        return this.catCardDao.update(id, catCard);
    }

    //deletes card
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/cards/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws CatCardNotFoundException {
        this.catCardDao.delete(id);
    }
}
