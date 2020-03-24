export class FindParamsDto {
  page: number = 0;
  limit: number = 10;
  q: string | undefined;
  sort: string | undefined;
  relations?: string;
}
