package com.app.ascension.api.division;

import com.app.ascension.model.division.Division;
import com.app.ascension.model.division.DivisionResponse;
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
@Api(tags = {"Divisions"})
public class DivisionController {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private DivisionRepo divisionRepo;

    @ApiOperation(value = "List of divisions", response = DivisionResponse.class)
    @RequestMapping(value = "/divisions", method = RequestMethod.GET)
    public DivisionResponse getAllDivisions(
        @ApiParam(value = ""    )               @RequestParam(value = "page"  ,  defaultValue="0"   ,  required = false) Integer page,
        @ApiParam(value = "between 1 to 1000" ) @RequestParam(value = "size"  ,  defaultValue="20"  ,  required = false) Integer size,
        @RequestParam(value = "id"  , required = false) Integer id,
        @RequestParam(value = "name"   , required = false) String  name,
        Pageable pageable
    ) {
        DivisionResponse resp = new DivisionResponse();
        Division qry = new Division();
        if (id != null)  { qry.setId(id); }
        if (name  != null)  { qry.setName(name); }

        Page<Division> divisionPage = divisionRepo.findAll(org.springframework.data.domain.Example.of(qry), pageable);
        resp.setPageStats(divisionPage, true);
        resp.setDivisions(divisionPage.getContent());
        return resp;
    }

    @ApiOperation(value = "Add new division", response = OperationResponse.class)
    @RequestMapping(value = "/divisions", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addNewDivision(@RequestBody Division division, HttpServletRequest req) {

        OperationResponse resp = new OperationResponse();

        try {
            List<Division> divisionRecords = this.divisionRepo.findAll();
            division.setId(divisionRecords.get(divisionRecords.size() - 1).getId());
            this.divisionRepo.save(division);
            resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Division added");
        } catch(Exception e) {
            resp.setOperationStatus(ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Unable to add division - already exist");
        } finally {
            return resp;
        }
    }

}
