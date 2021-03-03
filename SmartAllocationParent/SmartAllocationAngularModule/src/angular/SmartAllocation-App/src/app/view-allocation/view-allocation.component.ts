import { Component, OnInit, ViewChild } from "@angular/core";
import { ViewAllocationServiceService } from "../view-allocation-service.service";
import { MatTableDataSource, MatPaginator, MatSort } from "@angular/material";
import { AllocationChangeRequest } from "../allocationChangeRequest";

@Component({
  selector: "app-view-allocation",
  templateUrl: "./view-allocation.component.html",
  styleUrls: ["./view-allocation.component.css"]
})
export class ViewAllocationComponent implements OnInit {
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
