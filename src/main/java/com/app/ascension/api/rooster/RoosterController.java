package com.app.ascension.api.rooster;

import com.app.ascension.model.division.Division;
import com.app.ascension.model.division.DivisionResponse;
import com.app.ascension.model.rooster.Rooster;
import com.app.ascension.model.rooster.RoosterResponse;
import io.swagger.annotations.*;
import javax.servlet.http.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import java.lang.*;
import java.util.List;

import com.app.ascension.repo.*;
import com.app.ascension.model.response.*;
import static com.app.ascension.model.response.OperationResponse.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Roosters"})
public class RoosterController {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private RoosterRepo roosterRepo;

    @ApiOperation(value = "Get list of roosters", response = RoosterResponse.class)
    @RequestMapping(value = "/roosters", method = RequestMethod.GET)
    public RoosterResponse getAllRoosters(
        @ApiParam(value = "")
        @RequestParam(value = "page", defaultValue="0", required = false) Integer page,
        @ApiParam(value = "between 1 to 1000" )
        @RequestParam(value = "size", defaultValue="20", required = false) Integer size,
        @RequestParam(value = "id", required = false) String id,
        Pageable pageable
    ) {
        RoosterResponse resp = new RoosterResponse();
        Rooster qry = new Rooster();
        if (id != null)
            qry.setId(id);

        List<Rooster> test = this.roosterRepo.findAll();
        for(int i =0; i<test.size();i++){
            System.out.println(test.get(i).getDivisionId());
        }
        // System.out.println(roosterRepo.exists(id));
        Page<Rooster> roosterPage = roosterRepo.findAll(org.springframework.data.domain.Example.of(qry), pageable);
        resp.setPageStats(roosterPage, true);
        resp.setRoosters(roosterPage.getContent());
        return resp;
    }
}
