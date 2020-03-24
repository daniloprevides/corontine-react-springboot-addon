export class PluginDto {
  id: string | undefined;
  name: string | undefined;
  description?: string;
  componentsUrl: string | undefined;
  apiUrl: string | undefined;
  addUrl?: string;
  removeUrl: string | undefined;
  updateUrl: string | undefined;
  getUrl: string | undefined;
  getAllUrl: string | undefined;
  accessToken: string | undefined;
  tokenType: string | undefined;
  pluginType: string | undefined;
  enabled: boolean | undefined;
  environment: string | undefined;
  clientId: string | undefined;
  components: Array<any> | undefined;
}
