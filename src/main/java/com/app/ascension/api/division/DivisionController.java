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

    @ApiOperation(value = "Get list of divisions", response = DivisionResponse.class)
    @RequestMapping(value = "/divisions", method = RequestMethod.GET)
    public DivisionResponse getAllDivisions(
        @ApiParam(value = "")
        @RequestParam(value = "page", defaultValue="0", required = false) Integer page,
        @ApiParam(value = "between 1 to 1000" )
        @RequestParam(value = "size",  defaultValue="20", required = false) Integer size,
        @RequestParam(value = "id", required = false) Integer id,
        @RequestParam(value = "name", required = false) String name,
        Pageable pageable
    ) {
        DivisionResponse response = new DivisionResponse();
        Division query = new Division();
        if (id != null)
            query.setId(id);

        if (name  != null)
            query.setName(name);

        Page<Division> divisionPage = divisionRepo.findAll(org.springframework.data.domain.Example.of(query), pageable);
        response.setPageStats(divisionPage, true);
        response.setDivisions(divisionPage.getContent());
        return response;
    }

    @ApiOperation(value = "Add new division", response = OperationResponse.class)
    @RequestMapping(value = "/divisions", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addNewDivision(
        @RequestBody Division division,
        HttpServletRequest request
    ) {
        OperationResponse response = new OperationResponse();

        try {
            List<Division> divisionRecords = this.divisionRepo.findAll();
            division.setId(divisionRecords.get(divisionRecords.size() - 1).getId());
            this.divisionRepo.save(division);
            response.setOperationStatus(ResponseStatusEnum.SUCCESS);
            response.setOperationMessage("Division added");
        } catch(Exception e) {
            response.setOperationStatus(ResponseStatusEnum.ERROR);
            response.setOperationMessage("Unable to add division - already exist");
        } finally {
            return response;
        }
    }

    @ApiOperation(value = "Update existing division", response = OperationResponse.class)
    @RequestMapping(value = "/divisions", method = RequestMethod.PUT, produces = {"application/json"})
    public OperationResponse updateExistingDivision(
        @RequestBody Division division,
        HttpServletRequest request
    ) {
        OperationResponse response = new OperationResponse();

        try {
            this.divisionRepo.save(division);
            response.setOperationStatus(ResponseStatusEnum.SUCCESS);
            response.setOperationMessage("Division updated");
        } catch(Exception e) {
            response.setOperationStatus(ResponseStatusEnum.ERROR);
            response.setOperationMessage("Unable to update division - not found");
        } finally {
            return response;
        }
    }

    @ApiOperation(value = "Delete division", response = OperationResponse.class)
    @RequestMapping(value = "/divisions", method = RequestMethod.DELETE, produces = {"application/json"})
    public OperationResponse deleteDivision(
        @RequestParam(value = "id", required = true) Integer id,
        HttpServletRequest request
    ) {
        OperationResponse response = new OperationResponse();

        try {
            divisionRepo.delete(id);
            response.setOperationStatus(ResponseStatusEnum.SUCCESS);
            response.setOperationMessage("Division deleted");
        } catch(Exception e) {
            response.setOperationStatus(ResponseStatusEnum.ERROR);
            response.setOperationMessage("Unable to delete division - not found");
        } finally {
            return response;
        }
    }

}
