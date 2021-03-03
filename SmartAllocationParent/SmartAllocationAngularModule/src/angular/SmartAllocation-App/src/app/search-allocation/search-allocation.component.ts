import { Component, OnInit, ViewChild } from "@angular/core";
import { ViewAllocationServiceService } from "../view-allocation-service.service";
import { Allocation } from "../allocation";
import { MatTableDataSource, MatPaginator, MatSort } from "@angular/material";
import { AllocationChangeRequest } from "../allocationChangeRequest";

@Component({
  selector: "app-search-allocation",
  templateUrl: "./search-allocation.component.html",
  styleUrls: ["./search-allocation.component.css"]
})
export class SearchAllocationComponent implements OnInit {
  public allocationChangeRequests: AllocationChangeRequest[];

  public isLoading: Boolean = true;

  public isError: Boolean = false;

  displayedColumns: string[] = [
    "requestId",
    "sector",
    "createdTime",
    "clientOrg",
    "state",
    "mutualFund",
    "targetWeight",
    "minimumTradeSize",
    "invstFrozenConfig",
    "overDraftLimit",
    "targetCash"
  ];

  public allocationChangeRequestsDataSource: MatTableDataSource<AllocationChangeRequest>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private viewAllocationService: ViewAllocationServiceService) {}

  ngOnInit() {
    this.viewAllocationService.getAllocation().subscribe(
      data => {
        this.allocationChangeRequests = data;
        this.isLoading = false;
        this.allocationChangeRequestsDataSource = new MatTableDataSource(this.allocationChangeRequests);
        this.allocationChangeRequestsDataSource.paginator = this.paginator;
        this.allocationChangeRequestsDataSource.sort = this.sort;
      },
      error => {
        this.isLoading = false;
        console.log(error.message);
        this.isError = true;
      }
    );
  }

  applyFilter(filterValue: string) {
    this.allocationChangeRequestsDataSource.filter = filterValue.trim().toLowerCase();
  }
}
