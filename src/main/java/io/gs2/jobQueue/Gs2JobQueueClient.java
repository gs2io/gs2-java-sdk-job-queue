/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.gs2.jobQueue;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.gs2.jobQueue.model.PushJob;
import io.gs2.util.EncodingUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.model.IGs2Credential;
import io.gs2.jobQueue.control.*;

/**
 * GS2 JobQueue API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2JobQueueClient extends AbstractGs2Client<Gs2JobQueueClient> {

	public static String ENDPOINT = "job-queue";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2JobQueueClient(IGs2Credential credential) {
		super(credential);
	}


	/**
	 * ジョブキューを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateQueueResult createQueue(CreateQueueRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());

        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getNotificationType() != null) body.put("notificationType", request.getNotificationType());
        if(request.getNotificationUrl() != null) body.put("notificationUrl", request.getNotificationUrl());
        if(request.getNotificationGameName() != null) body.put("notificationGameName", request.getNotificationGameName());
		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/queue",
				credential,
				ENDPOINT,
				CreateQueueRequest.Constant.MODULE,
				CreateQueueRequest.Constant.FUNCTION,
				body.toString());


		return doRequest(post, CreateQueueResult.class);

	}


	/**
	 * デッドジョブを削除します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteDeadJob(DeleteDeadJobRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/deadJob/" + (request.getJobId() == null || request.getJobId().equals("") ? "null" : request.getJobId()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteDeadJobRequest.Constant.MODULE,
				DeleteDeadJobRequest.Constant.FUNCTION);


		doRequest(delete, null);

	}


	/**
	 * ジョブキューを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteQueue(DeleteQueueRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteQueueRequest.Constant.MODULE,
				DeleteQueueRequest.Constant.FUNCTION);


		doRequest(delete, null);

	}


	/**
	 * デッドジョブの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeDeadJobResult describeDeadJob(DescribeDeadJobRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/deadJob";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeDeadJobRequest.Constant.MODULE,
				DescribeDeadJobRequest.Constant.FUNCTION);


		return doRequest(get, DescribeDeadJobResult.class);

	}


	/**
	 * スクリプト名で絞り込んでデッドジョブの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeDeadJobByScriptNameResult describeDeadJobByScriptName(DescribeDeadJobByScriptNameRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/deadJob/script/" + (request.getScriptName() == null || request.getScriptName().equals("") ? "null" : request.getScriptName()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeDeadJobByScriptNameRequest.Constant.MODULE,
				DescribeDeadJobByScriptNameRequest.Constant.FUNCTION);


		return doRequest(get, DescribeDeadJobByScriptNameResult.class);

	}


	/**
	 * ユーザIDで絞り込んでデッドジョブの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeDeadJobByUserIdResult describeDeadJobByUserId(DescribeDeadJobByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/deadJob/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeDeadJobByUserIdRequest.Constant.MODULE,
				DescribeDeadJobByUserIdRequest.Constant.FUNCTION);


		return doRequest(get, DescribeDeadJobByUserIdResult.class);

	}


	/**
	 * ジョブの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeJobResult describeJob(DescribeJobRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/job";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeJobRequest.Constant.MODULE,
				DescribeJobRequest.Constant.FUNCTION);


		return doRequest(get, DescribeJobResult.class);

	}


	/**
	 * ジョブの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeJobByUserIdResult describeJobByUserId(DescribeJobByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/job/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeJobByUserIdRequest.Constant.MODULE,
				DescribeJobByUserIdRequest.Constant.FUNCTION);


		return doRequest(get, DescribeJobByUserIdResult.class);

	}


	/**
	 * ジョブの実行結果の一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeJobResultResult describeJobResult(DescribeJobResultRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/deadJob/" + (request.getJobId() == null || request.getJobId().equals("") ? "null" : request.getJobId()) + "/result";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeJobResultRequest.Constant.MODULE,
				DescribeJobResultRequest.Constant.FUNCTION);


		return doRequest(get, DescribeJobResultResult.class);

	}


	/**
	 * ジョブキューの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeQueueResult describeQueue(DescribeQueueRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeQueueRequest.Constant.MODULE,
				DescribeQueueRequest.Constant.FUNCTION);


		return doRequest(get, DescribeQueueResult.class);

	}


	/**
	 * ジョブキューを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetQueueResult getQueue(GetQueueRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetQueueRequest.Constant.MODULE,
				GetQueueRequest.Constant.FUNCTION);


		return doRequest(get, GetQueueResult.class);

	}


	/**
	 * ジョブキューにジョブを登録します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public PushResult push(PushRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
		ArrayNode jobs = body.putArray("jobs");
		for(PushJob job : request.getJobs()) {
			jobs.add(
					JsonNodeFactory.instance.objectNode()
							.put("scriptName", job.getScriptName())
							.put("args", job.getArgs())
							.put("maxRetry", job.getMaxRetry())
			);
		}

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "/job/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "",
				credential,
				ENDPOINT,
				PushRequest.Constant.MODULE,
				PushRequest.Constant.FUNCTION,
				body.toString());


		return doRequest(post, PushResult.class);

	}


	/**
	 * ジョブキューを実行します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public RunResult run(RunRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "",
				credential,
				ENDPOINT,
				RunRequest.Constant.MODULE,
				RunRequest.Constant.FUNCTION,
				body.toString());

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, RunResult.class);

	}


	/**
	 * ジョブキューを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateQueueResult updateQueue(UpdateQueueRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getNotificationType() != null) body.put("notificationType", request.getNotificationType());
        if(request.getNotificationUrl() != null) body.put("notificationUrl", request.getNotificationUrl());
        if(request.getNotificationGameName() != null) body.put("notificationGameName", request.getNotificationGameName());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/queue/" + (request.getQueueName() == null || request.getQueueName().equals("") ? "null" : request.getQueueName()) + "",
				credential,
				ENDPOINT,
				UpdateQueueRequest.Constant.MODULE,
				UpdateQueueRequest.Constant.FUNCTION,
				body.toString());


		return doRequest(put, UpdateQueueResult.class);

	}


}